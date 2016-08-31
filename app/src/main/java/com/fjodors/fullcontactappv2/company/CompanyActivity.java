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

        Company company = Parcels.unwrap(getIntent().getParcelableExtra(SearchActivity.COMPANY_KEY));

//        Observable o1 = Observable.just(company.getOrganization().getLinks());
//        Observable o2 = Observable.just(company.getOrganization().getContactInfo().getEmailAddresses());
//        Observable o3 = Observable.just(company.getSocialProfiles());
//
//        Observable.zip(
//                o1,
//                o2,
//                o3,
//                (a, b, c) -> {
//                    List<Object> list = new ArrayList<>();
//                    list.addAll(o1.toList());
//                    list.addAll(o2);
//                    list.addAll(o3);
//                    return list;
//                }
//        );


        initCompanyRecyclerView(company);
    }

    private void initCompanyRecyclerView(Company company) {
        companyDetailRecyclerView.setHasFixedSize(true);
        companyDetailRecyclerView.setLayoutManager(linearLayoutManager);
        companyRecyclerAdapter.setCompany(company);
        companyDetailRecyclerView.setAdapter(companyRecyclerAdapter);
    }
}
