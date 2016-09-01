package com.fjodors.fullcontactappv2.search;

import com.fjodors.fullcontactappv2.api.CompanyManager;
import com.fjodors.fullcontactappv2.company.CompanyDetailHeader;

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
    public void fetchCompanyDataForListView(String companyDomain) {
        companyManager.getCompanyData(companyDomain)
                .doOnTerminate(() -> searchView.hideProgress())
                .map(company -> Observable.merge(
                        Observable.just(new CompanyDetailHeader(company.getLogo(),
                                company.getOrganization().getName(),
                                company.getOrganization().getApproxEmployees(),
                                company.getOrganization().getFounded())),
                        Observable.from(company.getOrganization().getLinks()),
                        Observable.from(company.getOrganization().getContactInfo().getEmailAddresses()),
                        Observable.from(company.getSocialProfiles()))
                        .toList()
                )
                .subscribe(companyData -> searchView.openCompanyDetail(companyData.toBlocking().single()),
                        e -> searchView.showErrorMsg(e));
    }
}
