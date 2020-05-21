package com.example.display;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class JokeActivity extends AppCompatActivity {

    public static final String JOKE_KEY = "joke_key";

    @Override
    protected void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);

        final Intent intent = getIntent();
        final String joke = intent.getStringExtra(JokeActivity.JOKE_KEY);

        if (joke != null && joke.length() != 0) {
            Toast.makeText(this, joke, Toast.LENGTH_SHORT).show();
        }
    }
}
