package com.fjodors.fullcontactappv2.search;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fjodors.fullcontactappv2.FullContactApp;
import com.fjodors.fullcontactappv2.R;
import com.fjodors.fullcontactappv2.company.CompanyActivity;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity implements SearchContract.View {
    public static final String COMPANY_KEY = "company";

    @BindView(R.id.searchBtn)
    Button searchBtn;
    @BindView(R.id.companyDomainET)
    EditText companyDomainET;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    @Inject
    SearchContract.Presenter searchPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        FullContactApp.get(this)
                .getFullContactAppComponent()
                .plus(new SearchModule(this))
                .inject(this);
    }

//TODO: do this in presenter

    /**
     * https://github.com/ReactiveX/RxJava/wiki/The-RxJava-Android-Module
     */
//    // MyActivity
//    private Subscription subscription;
//
//    protected void onCreate(Bundle savedInstanceState) {
//        this.subscription = observable.subscribe(this);
//    }
//
//    ...
//
//    protected void onDestroy() {
//        this.subscription.unsubscribe();
//        super.onDestroy();
//    }
    @OnClick(R.id.searchBtn)
    public void fetchDomain() {
        searchPresenter.fetchCompanyDataForListView(companyDomainET.getText().toString());
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
}
