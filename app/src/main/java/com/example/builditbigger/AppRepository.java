package com.example.builditbigger;

import androidx.test.espresso.idling.CountingIdlingResource;

import com.example.builditbigger.backend.myApi.MyApi;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;

import javax.annotation.Nonnull;

import static com.google.api.client.extensions.android.http.AndroidHttp.newCompatibleTransport;

/*
 * Combining Pattern of Repo/Source/UseCase since its really only for one purpose in this project.
 */
public class AppRepository {

    private static final String ROOT_URL = "http://10.0.2.2:8080/_ah/api/";

    @Nonnull
    private final MyApi myApiService;

    @Nonnull
    private final CountingIdlingResource countingIdlingResource =
            new CountingIdlingResource("idling_resource");

    public AppRepository() {
        myApiService = createMyApiBuilder().build();
    }

    @Nonnull
    public final MyApi getMyApiService() {
        return myApiService;
    }

    @Nonnull
    public final CountingIdlingResource getCountingIdlingResource() {
        return countingIdlingResource;
    }

    @Nonnull
    private MyApi.Builder createMyApiBuilder() {
        final MyApi.Builder builder =
                new MyApi.Builder(newCompatibleTransport(), new AndroidJsonFactory(), null);
        return setDevOptionsToMyApiBuilder(builder);
    }

    @Nonnull
    private MyApi.Builder setDevOptionsToMyApiBuilder(@Nonnull final MyApi.Builder builder) {
        return builder
                .setRootUrl(ROOT_URL)
                .setGoogleClientRequestInitializer(request -> request.setDisableGZipContent(true));
    }
}