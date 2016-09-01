package com.fjodors.fullcontactappv2.search;

import java.util.List;

public interface SearchContract {
    interface View {
        void showErrorMsg(Throwable e);

        void showProgress();

        void hideProgress();

        void openCompanyDetail(List<Object> companyData);
    }

    interface Presenter {
        void fetchCompanyDataForListView(String companyDomain);
    }
}
