package com.example.moviesapp.ui.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.moviesapp.model.local.TvShow;
import com.example.moviesapp.repository.TvShowRepository;

import java.util.List;

public class TvShowViewModel extends ViewModel {
    private TvShowRepository repository;

    public TvShowViewModel() {
        repository = TvShowRepository.getInstance();
    }

    public LiveData<List<TvShow>> getShows() {
        return repository.getShowCollection();
    }
}
