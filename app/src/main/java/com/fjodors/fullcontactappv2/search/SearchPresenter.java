package com.fjodors.fullcontactappv2.search;

import com.fjodors.fullcontactappv2.api.FullContactManager;

import javax.inject.Inject;

public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View searchView;
    private FullContactManager fullContactManager;

    @Inject
    public SearchPresenter(SearchContract.View searchView, FullContactManager fullContactManager) {
        this.searchView = searchView;
        this.fullContactManager = fullContactManager;
    }

    @Override
    public void fetchCompanyData(String companyDomain) {
        fullContactManager.getCompanyData(companyDomain)
                .doOnTerminate(() -> searchView.hideProgress())
                .subscribe(company -> searchView.openCompanyDetail(company),
                        e -> searchView.showErrorMsg(e));

    }
}
