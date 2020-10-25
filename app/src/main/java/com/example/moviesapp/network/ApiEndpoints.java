package com.example.moviesapp.network;

import com.example.moviesapp.model.response.CastResponse;
import com.example.moviesapp.model.response.GenreResponse;
import com.example.moviesapp.model.response.MovieResponse;
import com.example.moviesapp.model.response.TvShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiEndpoints {

    @GET("discover/movie") // get movies data
    Call<MovieResponse> getMovies(@Query("api_key") String apiKey, @Query("language") String lang);

    @GET("discover/tv") // get tvShows data
    Call<TvShowResponse> getTvShows(@Query("api_key") String apiKey, @Query("language") String lang);

    @GET("{type}/{id}") // get details (if needed) and genres of specific movie / tv shows
    Call<GenreResponse> getGenres(@Path("type") String type, @Path("id") int id, @Query("api_key") String apiKey);

    @GET("{type}/{id}/credits") // get casts of specific movie / tv shows
    Call<CastResponse> getCasts(@Path("type") String type, @Path("id") int id, @Query("api_key") String apiKey);
}
