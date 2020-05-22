package com.example.builditbigger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.builditbigger.backend.myApi.MyApi;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MainActivityFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(
            @Nonnull final LayoutInflater inflater,
            @Nullable final ViewGroup container,
            @Nullable final Bundle savedInstanceState
    ) {
        final View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        final AdView mAdView = rootView.findViewById(R.id.adView);
        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device.
        final AdRequest adRequest = new AdRequest.Builder().build();

        mAdView.loadAd(adRequest);

        final MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        mainViewModel.init(getMyApi());

        return rootView;
    }

    @Nonnull
    private MyApi getMyApi() {
        final BuildItBiggerApplication application =
                ((BuildItBiggerApplication) requireActivity().getApplication());
        final AppRepository appRepository = application.getAppRepository();


        return appRepository.getMyApiService();
    }
}