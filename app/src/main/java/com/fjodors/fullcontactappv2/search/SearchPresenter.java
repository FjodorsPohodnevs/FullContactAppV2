package com.fjodors.fullcontactappv2.search;

import android.content.SharedPreferences;

import com.fjodors.fullcontactappv2.api.CompanyManager;
import com.fjodors.fullcontactappv2.company.CompanyDetailHeader;

import java.util.Set;

import javax.inject.Inject;

import rx.Observable;

public class SearchPresenter implements SearchContract.Presenter {
    public static final String PREFS_SEARCH_HISTORY = "searchHistory";

    private SearchContract.View searchView;
    private CompanyManager companyManager;

    private Set<String> history;
    private SharedPreferences sharedPreferences;

    @Inject
    public SearchPresenter(SearchContract.View searchView, CompanyManager companyManager, SharedPreferences sharedPreferences, Set<String> history) {
        this.searchView = searchView;
        this.companyManager = companyManager;
        this.sharedPreferences = sharedPreferences;
        this.history = history;
    }

    @Override
    public void fetchCompanyDataForListView(String companyDomain) {
        searchView.showProgress();

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
                .subscribe(companyData -> {
                            searchView.openCompanyDetail(companyData.toBlocking().single());
                            addSearchInput(companyDomain);
                            savePrefs();
                        },
                        e -> searchView.showErrorMsg(e));
    }

    private void addSearchInput(String input) {
        if (!history.contains(input)) {
            history.add(input);
            searchView.setAutoCompleteSource(history);
        }
    }

    private void savePrefs() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(PREFS_SEARCH_HISTORY, history);
        editor.apply();
    }
}
