package com.fjodors.fullcontactappv2.search;

import java.util.List;
import java.util.Set;

public interface SearchContract {
    interface View {
        void showErrorMsg(Throwable e);

        void showProgress();

        void hideProgress();

        void openCompanyDetail(List<Object> companyData);

        void setAutoCompleteSource(Set<String> history);
    }

    interface Presenter {
        void fetchCompanyDataForListView(String companyDomain);
    }
}
