package com.fjodors.fullcontactappv2.companyDetail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fjodors.fullcontactappv2.R;
import com.fjodors.fullcontactappv2.api.responses.Company;
import com.fjodors.fullcontactappv2.search.SearchActivity;

import org.parceler.Parcels;

import butterknife.ButterKnife;

public class CompanyDetailActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_detail);
        ButterKnife.bind(this);

        Company company = (Company) Parcels.unwrap(getIntent().getParcelableExtra(SearchActivity.COMPANY_KEY));

    }
}
