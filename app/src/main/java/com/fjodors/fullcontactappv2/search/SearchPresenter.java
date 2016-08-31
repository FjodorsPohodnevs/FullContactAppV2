package com.fjodors.fullcontactappv2.search;

import com.fjodors.fullcontactappv2.api.CompanyManager;

import javax.inject.Inject;

import rx.Observable;

public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View searchView;
    private CompanyManager companyManager;

    @Inject
    public SearchPresenter(SearchContract.View searchView, CompanyManager companyManager) {
        this.searchView = searchView;
        this.companyManager = companyManager;
    }

    @Override
    public void fetchCompanyData(String companyDomain) {
        companyManager.getCompanyData(companyDomain)
                .doOnTerminate(() -> searchView.hideProgress())
                .map(company -> Observable.just(company.getOrganization().getLinks(),
                        company.getOrganization().getContactInfo().getEmailAddresses(),
                        company.getSocialProfiles()).toList())
                .subscribe(company -> searchView.openCompanyDetail(company),
                        e -> searchView.showErrorMsg(e));

    }
}
