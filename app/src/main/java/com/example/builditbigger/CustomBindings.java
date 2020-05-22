package com.example.builditbigger;

import android.view.View;

import androidx.databinding.BindingAdapter;

public class CustomBindings {

    @BindingAdapter("visibleOrGone")
    public static void setVisibleOrGone(final View view, final Boolean visible) {
        if (visible != null) {
            view.setVisibility(visible ? View.VISIBLE : View.GONE);
        }
    }
}