package com.fjodors.fullcontactappv2.search;

import com.fjodors.fullcontactappv2.ActivityScope;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(modules = SearchModule.class)
public interface SearchComponent {
    void inject(SearchActivity searchActivity);
}
