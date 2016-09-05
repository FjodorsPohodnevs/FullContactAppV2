package com.fjodors.fullcontactappv2.search;

import java.util.List;
import java.util.Set;

public interface SearchContract {
    interface SearchView {
        void showErrorMsg(int errorMsg);

        void hideErrorMsg();

        void showProgress();

        void hideProgress();

        void openCompanyDetail(List<Object> companyData);

        void setAutoCompleteSource(Set<String> history);

        void hideKeyboard();
    }

    interface SearchPresenter {
        void fetchCompanyData(String companyDomain);

        void addDomainInputToHistory(String input);

        void saveDomainPrefs();
    }
}
