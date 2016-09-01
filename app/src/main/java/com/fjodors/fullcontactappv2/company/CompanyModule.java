package com.fjodors.fullcontactappv2.company;

import android.support.v7.widget.LinearLayoutManager;

import com.fjodors.fullcontactappv2.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class CompanyModule {
    private CompanyActivity companyActivity;

    public CompanyModule(CompanyActivity companyActivity) {
        this.companyActivity = companyActivity;
    }

    @Provides
    @ActivityScope
    CompanyRecyclerAdapter provideCompanyAdapter() {
        return new CompanyRecyclerAdapter();
    }

    @Provides
    @ActivityScope
    LinearLayoutManager provideLinearLayoutManager() {
        return new LinearLayoutManager(companyActivity);
    }
}
