package com.fjodors.fullcontactappv2.search;

import com.fjodors.fullcontactappv2.ActivityScope;
import com.fjodors.fullcontactappv2.api.FullContactManager;

import dagger.Module;
import dagger.Provides;

@Module
public class SearchModule {

    private SearchContract.View searchView;

    public SearchModule(SearchContract.View searchView) {
        this.searchView = searchView;
    }

    @Provides
    @ActivityScope
    SearchContract.View provideSearchView() {
        return searchView;
    }

    @Provides
    @ActivityScope
    SearchContract.Presenter searchPresenter(FullContactManager fullContactManager) {
        return new SearchPresenter(searchView, fullContactManager);
    }

}
