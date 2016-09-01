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
                .map(company -> Observable.merge(
                        //Change objects and lists to observable which emit streams, then combine all observables and transform into single list
                        //We must combine everything because RecyclerAdapter must have single data set, which will be List<Object>companyData
                        Observable.just(company),//From here we get company logo
                        Observable.just(company.getOrganization()),//from here company name, founded year and employee count
                        Observable.from(company.getOrganization().getLinks()),//from here we get urls
                        Observable.from(company.getOrganization().getContactInfo().getEmailAddresses()),//from here we get emails
                        Observable.from(company.getSocialProfiles()))//from here we get social networks names and bio text
                        .toList()
                )
                .subscribe(companyData -> searchView.openCompanyDetail(companyData.toBlocking().single()),
                        e -> searchView.showErrorMsg(e));

    }
}
