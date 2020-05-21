package com.example.builditbigger.backend;

import javax.annotation.Nonnull;

/**
 * The object model for the data we are sending through endpoints
 */
public class MyBean {

    private String myData;

    @Nonnull
    public final String getData() {
        return myData;
    }

    public void setData(@Nonnull final String data) {
        myData = data;
    }
}