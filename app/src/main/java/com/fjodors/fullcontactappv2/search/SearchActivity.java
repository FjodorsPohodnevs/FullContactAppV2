package com.fjodors.fullcontactappv2.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.fjodors.fullcontactappv2.FullContactApp;
import com.fjodors.fullcontactappv2.R;
import com.fjodors.fullcontactappv2.company.CompanyActivity;

import org.parceler.Parcels;

import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity implements SearchContract.SearchView {
    public static final String EXTRA_COMPANY = "com.fjodors.fullcontactappv2.search.EXTRA_COMPANY";

    @BindView(R.id.searchBtn)
    Button searchBtn;
    @BindView(R.id.companyDomainET)
    AutoCompleteTextView companyDomainAC;
    @BindView(R.id.progressContainer)
    FrameLayout progressContainer;
    @BindView(R.id.errorMsgTV)
    TextView errorMsgTV;

    @Inject
    SearchContract.SearchPresenter searchSearchPresenter;

    @Inject
    Set<String> history;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        FullContactApp.get(this)
                .getFullContactAppComponent()
                .plus(new SearchModule(this))
                .inject(this);

        setAutoCompleteSource(history);
    }

    @OnClick(R.id.searchBtn)
    public void fetchDomain() {
        searchSearchPresenter.fetchCompanyData(companyDomainAC.getText().toString());
        hideErrorMsg();
        hideKeyboard();
    }

    @Override
    public void showErrorMsg(int errorMsg) {
        errorMsgTV.setText(errorMsg);
        errorMsgTV.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideErrorMsg() {
        errorMsgTV.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressContainer.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressContainer.setVisibility(View.GONE);
    }

    @Override
    public void openCompanyDetail(List<Object> companyData) {
        Intent intent = new Intent(this, CompanyActivity.class);
        intent.putExtra(EXTRA_COMPANY, Parcels.wrap(companyData));
        startActivity(intent);
    }

    @Override
    public void setAutoCompleteSource(Set<String> history) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, history.toArray(new String[history.size()]));
        companyDomainAC.setAdapter(adapter);
    }

    @Override
    public void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
}
