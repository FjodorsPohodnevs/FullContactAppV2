package com.fjodors.fullcontactappv2.company;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.fjodors.fullcontactappv2.FullContactApp;
import com.fjodors.fullcontactappv2.R;
import com.fjodors.fullcontactappv2.search.SearchActivity;

import org.parceler.Parcels;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CompanyActivity extends AppCompatActivity {
    @BindView(R.id.companyDetailRecyclerView)
    RecyclerView companyDetailRecyclerView;
    @BindView(R.id.toolbar)
    Toolbar myToolbar;


    @Inject
    CompanyRecyclerAdapter companyRecyclerAdapter;
    @Inject
    LinearLayoutManager linearLayoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        ButterKnife.bind(this);

        FullContactApp.get(this)
                .getFullContactAppComponent()
                .plus(new CompanyModule(this))
                .inject(this);


        List<Object> companyData = Parcels.unwrap(getIntent()
                .getParcelableExtra(SearchActivity.EXTRA_COMPANY));

        initToolBar(((CompanyDetailHeader) companyData.get(0)).getName());
        initCompanyRecyclerView(companyData);
    }

    private void initToolBar(String companyName) {
        myToolbar.setTitle(companyName);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initCompanyRecyclerView(List<Object> companyData) {
        companyDetailRecyclerView.setHasFixedSize(true);
        companyDetailRecyclerView.setLayoutManager(linearLayoutManager);
        companyRecyclerAdapter.setCompany(companyData);
        companyDetailRecyclerView.setAdapter(companyRecyclerAdapter);
        RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(this, R.drawable.divider_company);
        companyDetailRecyclerView.addItemDecoration(itemDecoration);
    }
}
