package com.fjodors.fullcontactappv2;

import com.fjodors.fullcontactappv2.api.FullContactApiModule;
import com.fjodors.fullcontactappv2.search.SearchComponent;
import com.fjodors.fullcontactappv2.search.SearchModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {FullContactAppModule.class, FullContactApiModule.class})
public interface FullContactAppComponent {

    SearchComponent plus(SearchModule searchModule);
}
