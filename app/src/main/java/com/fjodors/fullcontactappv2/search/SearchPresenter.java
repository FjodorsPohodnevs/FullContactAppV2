package com.fjodors.fullcontactappv2.search;

import android.content.SharedPreferences;

import com.fjodors.fullcontactappv2.api.CompanyManager;
import com.fjodors.fullcontactappv2.api.ErrorManager;
import com.fjodors.fullcontactappv2.api.response.Company;
import com.fjodors.fullcontactappv2.company.CompanyDetailHeader;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import retrofit2.adapter.rxjava.HttpException;
import rx.exceptions.Exceptions;


public class SearchPresenter implements SearchContract.SearchPresenter {
    private SearchContract.SearchView searchSearchView;
    private CompanyManager companyManager;

    private Set<String> domainHistory;
    private SharedPreferences sharedPreferences;

    @Inject
    public SearchPresenter(SearchContract.SearchView searchSearchView,
                           CompanyManager companyManager,
                           SharedPreferences sharedPreferences,
                           Set<String> domainHistory) {
        this.searchSearchView = searchSearchView;
        this.companyManager = companyManager;
        this.sharedPreferences = sharedPreferences;
        this.domainHistory = domainHistory;
    }

    @Override
    public void fetchCompanyData(String companyDomain) {
        searchSearchView.showProgress();
        companyManager.getCompanyData(companyDomain)
                .doOnTerminate(() -> searchSearchView.hideProgress())
                .map(company -> {
                    if (company.getStatus() == HttpURLConnection.HTTP_ACCEPTED) {
                        throw Exceptions.propagate(new Error(String.valueOf(HttpURLConnection.HTTP_ACCEPTED)));
                    } else {
                        return createCompanyDataSource(company);
                    }
                })
                .subscribe(companyData -> {
                    searchSearchView.openCompanyDetail(companyData);
                    addDomainInputToHistory(companyDomain);
                    saveDomainPrefs();
                }, e -> {
                    if (e instanceof HttpException) {
                        searchSearchView.showErrorMsg(ErrorManager.getErrorMessage(((HttpException) e).code()));
                    } else {
                        searchSearchView.showErrorMsg(ErrorManager.getErrorMessage(e));
                    }
                    searchSearchView.hideProgress();
                }, () -> searchSearchView.hideProgress());
    }

    @Override
    public void addDomainInputToHistory(String input) {
        if (!domainHistory.contains(input)) {
            domainHistory.add(input);
            searchSearchView.setAutoCompleteSource(domainHistory);
        }
    }

    @Override
    public void saveDomainPrefs() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putStringSet(SearchModule.SET_SEARCH_HISTORY, domainHistory);
        editor.apply();
    }


    private List<Object> createCompanyDataSource(Company company) {
        List<Object> companyData = new ArrayList<>();
        if (company.getOrganization() != null) {
            companyData.add(new CompanyDetailHeader(company.getLogo(),
                    company.getOrganization()
                            .getName(),
                    company.getOrganization()
                            .getApproxEmployees(),
                    company.getOrganization()
                            .getFounded()));
            companyData.addAll(company.getOrganization()
                    .getLinks());
            if (company.getOrganization()
                    .getContactInfo() != null) {
                companyData.addAll(company.getOrganization()
                        .getContactInfo()
                        .getEmailAddresses());
            }
            companyData.addAll(company.getSocialProfiles());
        }
        return companyData;
    }
}
