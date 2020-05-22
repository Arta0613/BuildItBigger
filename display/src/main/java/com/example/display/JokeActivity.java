package com.example.display;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE_KEY = "joke_key";

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        final Intent intent = getIntent();
        final String joke = intent.getStringExtra(JokeActivity.JOKE_KEY);

        if (joke != null && joke.length() != 0) {
            ((AppCompatTextView) findViewById(R.id.joke_text)).setText(joke);
        }
    }
}
