package com.example.moviesapp.ui.movie;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moviesapp.R;
import com.example.moviesapp.model.local.Movie;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieFragment extends Fragment {

    @BindView(R.id.progressBar)
    ProgressBar loading;
    @BindView(R.id.rv_movie)
    RecyclerView rvMovies;
    @BindView(R.id.listError)
    TextView tvError;

    private MovieViewModel viewModel;
    private MovieAdapter movieAdapter;

    public MovieFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        showLoading(true);
        viewModel = ViewModelProviders.of(requireActivity()).get(MovieViewModel.class);
        viewModel.getMovie().observe(requireActivity(), observeViewModel);

        rvMovies.setLayoutManager(new LinearLayoutManager(getActivity()));
        movieAdapter = new MovieAdapter(getActivity());
    }

    private Observer<List<Movie>> observeViewModel = movies -> {
        if (movies != null) {
            movieAdapter.setMovies(movies);
            movieAdapter.notifyDataSetChanged();
            rvMovies.setAdapter(movieAdapter);
            showLoading(false);
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            rvMovies.setVisibility(View.GONE);
            tvError.setVisibility(View.GONE);
            loading.setVisibility(View.VISIBLE);
        } else {
            rvMovies.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
        }
    }
}