package com.example.builditbigger;

import android.app.Application;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.RequestConfiguration;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;

public class BuildItBiggerApplication extends Application {

    @Nonnull
    private final AppRepository appRepository = new AppRepository();

    @Override
    public void onCreate() {
        super.onCreate();

        final List<String> testDevices = new ArrayList<>();
        testDevices.add(AdRequest.DEVICE_ID_EMULATOR);
        MobileAds.setRequestConfiguration(
                new RequestConfiguration.Builder().setTestDeviceIds(testDevices).build()
        );
    }

    @Nonnull
    public final AppRepository getAppRepository() {
        return appRepository;
    }
}