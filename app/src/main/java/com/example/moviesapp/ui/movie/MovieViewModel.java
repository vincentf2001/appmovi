package com.example.moviesapp.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviesapp.model.local.Movie;
import com.example.moviesapp.repository.MovieRepository;

import java.util.List;

public class MovieViewModel extends ViewModel {
    private MovieRepository repository;

    public MovieViewModel() {
        repository = MovieRepository.getInstance();
    }

    public LiveData<List<Movie>> getMovie() {
        return repository.getMovieCollection();
    }
}
