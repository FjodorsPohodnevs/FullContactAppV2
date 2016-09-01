package com.fjodors.fullcontactappv2.search;

import com.fjodors.fullcontactappv2.ActivityScope;
import com.fjodors.fullcontactappv2.api.CompanyManager;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchModule {
    private SearchContract.View searchActivity;

    public SearchModule(SearchContract.View searchActivity) {
        this.searchActivity = searchActivity;
    }

    @Provides
    @ActivityScope
    SearchContract.View provideSearchActivity() {
        return searchActivity;
    }

    @Provides
    @ActivityScope
    SearchContract.Presenter searchPresenter(CompanyManager companyManager) {
        return new SearchPresenter(searchActivity, companyManager);
    }
}
