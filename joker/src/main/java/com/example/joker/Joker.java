package com.example.joker;

import java.util.Random;

public class Joker {

    private final Random random = new Random();
    private final String[] jokes = {
            "Funny Joke 1",
            "Funny Joke 2",
            "Funny Joke 3",
            "Funny Joke 4",
            "Funny Joke 5"
    };

    public final String joke() {
        return getRandomJoke();
    }

    private String getRandomJoke() {
        return jokes[random.nextInt(jokes.length)];
    }
}