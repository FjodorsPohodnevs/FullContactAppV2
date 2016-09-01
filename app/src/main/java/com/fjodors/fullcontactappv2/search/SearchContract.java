package com.fjodors.fullcontactappv2.search;

import com.fjodors.fullcontactappv2.api.responses.Company;

import java.util.List;

public interface SearchContract {

    interface View {
        void showErrorMsg(Throwable e);

        void showProgress();

        void hideProgress();

        void openCompanyDetail(List<Object> companyData);
    }

    interface Presenter {
        void fetchCompanyData(String companyDomain);
    }
}
