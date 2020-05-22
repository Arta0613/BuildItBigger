package com.example.builditbigger;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.test.espresso.idling.CountingIdlingResource;

import com.example.builditbigger.backend.myApi.MyApi;
import com.example.builditbigger.databinding.FragmentMainBinding;
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
        final FragmentMainBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false);

        final AdView adView = binding.getRoot().findViewById(R.id.adView);
        if (adView != null) {
            // Create an ad request. Check logcat output for the hashed device ID to
            // get test ads on a physical device.
            final AdRequest adRequest = new AdRequest.Builder().build();

            adView.loadAd(adRequest);
        }

        final MainViewModel mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setViewModel(mainViewModel);

        mainViewModel.init(getMyApi(), getIdlingResource());

        return binding.getRoot();
    }

    @Nonnull
    private CountingIdlingResource getIdlingResource() {
        return getAppRepository().getCountingIdlingResource();
    }

    @Nonnull
    private MyApi getMyApi() {
        return getAppRepository().getMyApiService();
    }

    @Nonnull
    private AppRepository getAppRepository() {
        final BuildItBiggerApplication application =
                ((BuildItBiggerApplication) requireActivity().getApplication());

        return application.getAppRepository();
    }
}