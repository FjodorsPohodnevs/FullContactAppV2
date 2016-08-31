package com.fjodors.fullcontactappv2.company;

import com.fjodors.fullcontactappv2.ActivityScope;

import dagger.Subcomponent;


@ActivityScope
@Subcomponent(modules = CompanyModule.class)
public interface CompanyComponent {
    void inject(CompanyActivity companyActivity);
}
