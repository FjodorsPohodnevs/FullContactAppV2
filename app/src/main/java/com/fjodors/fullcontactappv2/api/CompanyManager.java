package com.fjodors.fullcontactappv2.api;

import com.fjodors.fullcontactappv2.api.responses.Company;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class CompanyManager {

    private FullContactApiService fullContactApiService;

    public CompanyManager(FullContactApiService fullContactApiService) {
        this.fullContactApiService = fullContactApiService;
    }

    public Observable<Company> getCompanyData(String companyDomain) {
        return fullContactApiService.getCompanyData(companyDomain)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
