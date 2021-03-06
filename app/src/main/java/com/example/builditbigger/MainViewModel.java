package com.example.builditbigger;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.test.espresso.idling.CountingIdlingResource;

import com.example.builditbigger.backend.myApi.MyApi;

import javax.annotation.Nonnull;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainViewModel extends ViewModel {

    private final CompositeDisposable disposable = new CompositeDisposable();
    private final SingleLiveEvent<String> jokeReceivedEvent = new SingleLiveEvent<>();

    private MyApi myApi;
    private CountingIdlingResource idlingResource;

    public final MutableLiveData<Boolean> loadingIndicator = new MutableLiveData<>(false);

    public void init(@Nonnull final MyApi myApi, @Nonnull final CountingIdlingResource idlingResource) {
        this.myApi = myApi;
        this.idlingResource = idlingResource;
    }

    @Override
    protected void onCleared() {
        disposable.clear();
        super.onCleared();
    }

    public void loadJoke() {
        loadingIndicator.setValue(true);
        idlingResource.increment();
        disposable.add(Single.fromCallable(() -> myApi.getJoke().execute().getData())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(handleJokeResponse())
        );

    }

    public SingleLiveEvent<String> getJokeReceivedEvent() {
        return jokeReceivedEvent;
    }

    @Nonnull
    private DisposableSingleObserver<String> handleJokeResponse() {
        return new DisposableSingleObserver<String>() {
            @Override
            public void onSuccess(@Nonnull final String joke) {
                jokeReceivedEvent.setValue(joke);
                loadingIndicator.setValue(false);
                idlingResource.decrement();
            }

            @Override
            public void onError(@Nonnull final Throwable e) {
                Log.e(MainViewModel.class.getSimpleName(), "onError: ", e);
                loadingIndicator.setValue(false);
                idlingResource.decrement();
            }
        };
    }
}