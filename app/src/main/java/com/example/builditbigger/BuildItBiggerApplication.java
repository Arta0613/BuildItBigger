package com.example.builditbigger;

import android.app.Application;

import javax.annotation.Nonnull;

public class BuildItBiggerApplication extends Application {

    private static final String FLAVOR = BuildConfig.FLAVOR;
    private static final String FREE = "free";

    @Nonnull
    private final AppRepository appRepository = new AppRepository();

    @Override
    public void onCreate() {
        super.onCreate();

        if (FLAVOR.equals(FREE)) {
            new MobileAdsConfiguration().init();
        }
    }

    @Nonnull
    public final AppRepository getAppRepository() {
        return appRepository;
    }
}