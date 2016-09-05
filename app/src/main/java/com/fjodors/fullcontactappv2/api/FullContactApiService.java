package com.fjodors.fullcontactappv2.api;

import com.fjodors.fullcontactappv2.api.response.Company;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface FullContactApiService {
    @GET("company/lookup.json")
    Observable<Company> getCompanyData(
            @Query("domain") String domain);
}
