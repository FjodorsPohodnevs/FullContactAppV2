package com.fjodors.fullcontactappv2.search;

import android.content.Context;
import android.content.SharedPreferences;

import com.fjodors.fullcontactappv2.ActivityScope;
import com.fjodors.fullcontactappv2.api.CompanyManager;

import java.util.HashSet;
import java.util.Set;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchModule {
    public static final String PREF_NAME = "PREF_DOMAIN";
    public static final String SET_SEARCH_HISTORY = "SET_SEARCH_HISTORY";

    private SearchContract.SearchView searchActivity;

    public SearchModule(SearchContract.SearchView searchActivity) {
        this.searchActivity = searchActivity;
    }

    @Provides
    @ActivityScope
    SearchContract.SearchView provideSearchActivity() {
        return searchActivity;
    }

    @Provides
    @ActivityScope
    SharedPreferences providesSharedPreferences() {
        return ((Context) searchActivity).getSharedPreferences(PREF_NAME, 0);
    }

    @Provides
    @ActivityScope
    Set<String> providesHistory(SharedPreferences sharedPreferences) {
        return sharedPreferences.getStringSet(SET_SEARCH_HISTORY, new HashSet<>());
    }


    @Provides
    @ActivityScope
    SearchContract.SearchPresenter searchPresenter(CompanyManager companyManager, SharedPreferences sharedPreferences, Set<String> history) {
        return new SearchPresenter(searchActivity, companyManager, sharedPreferences, history);
    }
}
