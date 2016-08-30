package com.fjodors.fullcontactappv2.search;

import com.fjodors.fullcontactappv2.api.responses.Company;

public interface SearchContract {

    interface View {
        void showErrorMsg(Throwable e);

        void showProgress();

        void hideProgress();

        void openCompanyDetail(Company company);
    }

    interface Presenter {
        void fetchCompanyData(String companyDomain);
    }
}
