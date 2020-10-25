package com.example.moviesapp.network;

import com.example.moviesapp.model.response.CastResponse;
import com.example.moviesapp.model.response.GenreResponse;
import com.example.moviesapp.model.response.MovieResponse;
import com.example.moviesapp.model.response.TvShowResponse;
import com.example.moviesapp.util.Constants;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService {
    private final ApiEndpoints api;
    private static RetrofitService service;
    private static final String TAG = "RetrofitService";

    private RetrofitService() {
        api = new Retrofit.Builder()
                .baseUrl(Constants.BaseSetting.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiEndpoints.class);
    }

    public static RetrofitService getInstance() {
        if (service == null) {
            service = new RetrofitService();
        }
        return service;
    }

    public Call<MovieResponse> getMovies() {
        return api.getMovies(Constants.BaseSetting.API_KEY, Constants.BaseSetting.LANGUAGE);
    }

    public Call<TvShowResponse> getTvShows() {
        return api.getTvShows(Constants.BaseSetting.API_KEY, Constants.BaseSetting.LANGUAGE);
    }

    public Call<GenreResponse> getGenres(String type, int id) {
        return api.getGenres(type, id, Constants.BaseSetting.API_KEY);
    }

    public Call<CastResponse> getCasts(String type, int id) {
        return api.getCasts(type, id, Constants.BaseSetting.API_KEY);
    }
}
