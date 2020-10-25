package com.example.moviesapp.util;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

public class Constants {

    @Retention(SOURCE)
    @StringDef
    public @interface BaseSetting {
        String BASE_URL = "https://api.themoviedb.org/3/";
        String API_KEY = "68eff651539ae197e48884a6d31d2059";
        String BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500";
        String LANGUAGE = "en-US";
    }

    @Retention(SOURCE)
    @StringDef
    public @interface Type {
        String MOVIES = "movie";
        String TV_SHOWS = "tv";
    }
}
