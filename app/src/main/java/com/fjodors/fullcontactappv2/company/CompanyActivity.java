package com.fjodors.fullcontactappv2.company;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fjodors.fullcontactappv2.FullContactApp;
import com.fjodors.fullcontactappv2.R;
import com.fjodors.fullcontactappv2.api.responses.Company;
import com.fjodors.fullcontactappv2.search.SearchActivity;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompanyActivity extends AppCompatActivity {

    @BindView(R.id.companyDetailRecyclerView)
    RecyclerView companyDetailRecyclerView;

    @Inject
    CompanyRecyclerAdapter companyRecyclerAdapter;
    @Inject
    LinearLayoutManager linearLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_detail);
        ButterKnife.bind(this);

        FullContactApp.get(this)
                .getFullContactAppComponent()
                .plus(new CompanyModule(this))
                .inject(this);

        List<Object> companyData = Parcels.unwrap(getIntent().getParcelableExtra(SearchActivity.COMPANY_KEY));
        initCompanyRecyclerView(companyData);
    }

    private void initCompanyRecyclerView(List<Object> companyData) {
        companyDetailRecyclerView.setHasFixedSize(true);
        companyDetailRecyclerView.setLayoutManager(linearLayoutManager);
        companyRecyclerAdapter.setCompany(companyData);
        companyDetailRecyclerView.setAdapter(companyRecyclerAdapter);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST);
        companyDetailRecyclerView.addItemDecoration(itemDecoration);
    }
}
