package com.fjodors.fullcontactappv2.search;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fjodors.fullcontactappv2.FullContactApp;
import com.fjodors.fullcontactappv2.R;
import com.fjodors.fullcontactappv2.company.CompanyActivity;

import org.parceler.Parcels;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity implements SearchContract.View {
    public static final String COMPANY_KEY = "company";

    @BindView(R.id.searchBtn)
    Button searchBtn;
    @BindView(R.id.companyDomainET)
    AutoCompleteTextView companyDomainAC;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Inject
    SearchContract.Presenter searchPresenter;

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
        searchPresenter.fetchCompanyDataForListView(companyDomainAC.getText().toString());
    }

    @Override
    public void showErrorMsg(Throwable e) {
        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void openCompanyDetail(List<Object> companyData) {
        Intent intent = new Intent(this, CompanyActivity.class);
        intent.putExtra(COMPANY_KEY, Parcels.wrap(companyData));
        startActivity(intent);
    }

    @Override
    public void setAutoCompleteSource(Set<String> history) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, history.toArray(new String[history.size()]));
        companyDomainAC.setAdapter(adapter);
    }
}
