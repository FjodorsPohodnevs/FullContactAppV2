package com.fjodors.fullcontactappv2.api;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class FullContactApiModule {

    public static final String API_BASE_URL = "https://api.fullcontact.com/v2/";

    private static String API_KEY = "16e01d26c38b8739";
    private static final String AUTHORIZATION_HEADER = "X-FullContact-APIKey";


    @Provides
    @Singleton
    public Gson provideGson() {
        GsonBuilder gsonBuilder = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    public Interceptor provideInterceptor() {
        return chain -> {
            Request newRequest = chain.request().newBuilder().addHeader(AUTHORIZATION_HEADER, API_KEY).build();
            return chain.proceed(newRequest);
        };
    }

//    @Provides
//    @Singleton
//    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
//        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
//        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        return loggingInterceptor;
//    }

    @Provides
    @Singleton
    public Cache provideOkHttpCache(Application application) {
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        return new Cache(application.getCacheDir(), cacheSize);
    }

    @Provides
    @Singleton
    public OkHttpClient provideOkHttpClient(Interceptor interceptor, Cache cache) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        //TODO: place this outside
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        builder.cache(cache)
                .addInterceptor(interceptor)
                .addInterceptor(loggingInterceptor);
        return builder.build();
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(okHttpClient)
                .build();
    }

    @Provides
    @Singleton
    public FullContactApiService provideFullContactApiService(Retrofit retrofit) {
        return retrofit.create(FullContactApiService.class);
    }

    @Provides
    @Singleton
    FullContactManager provideFullContactManager(FullContactApiService fullContactApiService) {
        return new FullContactManager(fullContactApiService);
    }

}