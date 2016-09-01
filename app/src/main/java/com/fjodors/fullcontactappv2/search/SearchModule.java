package com.fjodors.fullcontactappv2.search;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.fjodors.fullcontactappv2.ActivityScope;
import com.fjodors.fullcontactappv2.api.CompanyManager;

import java.util.HashSet;
import java.util.Set;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchModule {
    public static final String PREFS_NAME = "domainPrefs";
    public static final String PREFS_SEARCH_HISTORY = "searchHistory";

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
    SharedPreferences providesSharedPreferences() {
        return ((Context) searchActivity).getSharedPreferences(PREFS_NAME, 0);
    }

    @Provides
    @ActivityScope
    Set<String> providesHistory(SharedPreferences sharedPreferences) {
        return sharedPreferences.getStringSet(PREFS_SEARCH_HISTORY, new HashSet<>());
    }


    @Provides
    @ActivityScope
    SearchContract.Presenter searchPresenter(CompanyManager companyManager, SharedPreferences sharedPreferences, Set<String> history) {
        return new SearchPresenter(searchActivity, companyManager, sharedPreferences, history);
    }
}
