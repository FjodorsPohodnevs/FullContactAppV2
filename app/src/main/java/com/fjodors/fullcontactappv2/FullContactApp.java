package com.fjodors.fullcontactappv2;

import android.app.Application;
import android.content.Context;


public class FullContactApp extends Application {


    private FullContactAppComponent fullContactAppComponent;

    public static FullContactApp get(Context context) {
        return (FullContactApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        fullContactAppComponent = DaggerFullContactAppComponent.builder()
                .fullContactAppModule(new FullContactAppModule(this))
                .build();
    }

    public FullContactAppComponent getFullContactAppComponent() {
        return fullContactAppComponent;
    }
}
