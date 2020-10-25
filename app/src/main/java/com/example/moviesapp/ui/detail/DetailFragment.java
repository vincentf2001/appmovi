package com.example.moviesapp.ui.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.R;
import com.example.moviesapp.model.local.Genre;
import com.example.moviesapp.model.local.Movie;
import com.example.moviesapp.model.local.TvShow;
import com.example.moviesapp.ui.MainActivity;
import com.example.moviesapp.util.Constants;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailFragment extends Fragment {

    @BindView(R.id.detail_cover)
    ImageView detailCover;
    @BindView(R.id.detail_poster)
    ImageView detailPoster;
    @BindView(R.id.layerHide)
    ImageView detailHide;
    @BindView(R.id.detail_title)
    TextView tvTitle;
    @BindView(R.id.detail_popular)
    TextView tvPopular;
    @BindView(R.id.detail_genre)
    TextView tvGenre;
    @BindView(R.id.detail_description)
    TextView tvDesc;
    @BindView(R.id.pb_detail)
    ProgressBar loading;
    @BindView(R.id.rv_cast)
    RecyclerView rvCast;

    private DetailViewModel viewModel;
    private DetailCastAdapter adapter;

    private Movie movie;
    private TvShow tvShow;

    public DetailFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        showLoading(true);
        viewModel = ViewModelProviders.of(requireActivity()).get(DetailViewModel.class);

        rvCast.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        adapter = new DetailCastAdapter(getActivity());

        if (getArguments() != null) {
            movie = DetailFragmentArgs.fromBundle(getArguments()).getMovie();
            tvShow = DetailFragmentArgs.fromBundle(getArguments()).getTvShow();

            if (movie != null) {
                initMovie(movie);
                observeMovieViewModel(Integer.parseInt(movie.getId_movie()));
            } else {
                initShow(tvShow);
                observeShowViewModel(Integer.parseInt(tvShow.getId_show()));
            }
        }
    }

    private void observeShowViewModel(int idShow) {
        viewModel.getShowGenre(idShow).observe(requireActivity(), genres -> {
            if (genres != null) {
                for (int i = 0; i < genres.size(); i++) {
                    Genre g = genres.get(i);
                    if (i < genres.size() - 1) {
                        tvGenre.append(g.getName() + " | ");
                    } else {
                        tvGenre.append(g.getName());
                    }
                }
            }
        });

        viewModel.getShowCast(idShow).observe(requireActivity(), casts -> {
            if (casts != null) {
                adapter.setCastData(casts);
                adapter.notifyDataSetChanged();
                rvCast.setAdapter(adapter);
                showLoading(false);
            }
        });
    }

    private void observeMovieViewModel(int idMovie) {
        viewModel.getMovieGenre(idMovie).observe(requireActivity(), genres -> {
            if (genres != null) {
                for (int i = 0; i < genres.size(); i++) {
                    Genre g = genres.get(i);
                    if (i < genres.size() - 1) {
                        tvGenre.append(g.getName() + " | ");
                    } else {
                        tvGenre.append(g.getName());
                    }
                }
            }
        });

        viewModel.getMovieCast(idMovie).observe(requireActivity(), casts -> {
            if (casts != null) {
                adapter.setCastData(casts);
                adapter.notifyDataSetChanged();
                rvCast.setAdapter(adapter);
                showLoading(false);
            }
        });
    }

    private void initShow(TvShow tvShow) {
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setTitle(tvShow.getTitle());
        Glide.with(getActivity()).load(Constants.BaseSetting.BASE_IMAGE_URL + tvShow.getCover()).into(detailCover);
        Glide.with(getActivity()).load(Constants.BaseSetting.BASE_IMAGE_URL + tvShow.getPoster()).into(detailPoster);
        tvTitle.setText(tvShow.getTitle());
        tvPopular.setText(tvShow.getPopularity());
        tvDesc.setText(tvShow.getDescription());
    }

    private void initMovie(Movie movie) {
        Objects.requireNonNull(((MainActivity) requireActivity()).getSupportActionBar()).setTitle(movie.getTitle());
        Glide.with(getActivity()).load(Constants.BaseSetting.BASE_IMAGE_URL + movie.getCover()).into(detailCover);
        Glide.with(getActivity()).load(Constants.BaseSetting.BASE_IMAGE_URL + movie.getPoster()).into(detailPoster);
        tvTitle.setText(movie.getTitle());
        tvPopular.setText(movie.getPopularity());
        tvDesc.setText(movie.getDescription());
    }

    private void showLoading(Boolean state) {
        if (state) {
            loading.setVisibility(View.VISIBLE);
            detailHide.setVisibility(View.VISIBLE);
        } else {
            loading.setVisibility(View.GONE);
            detailHide.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fade_out));
            detailHide.setVisibility(View.GONE);
            detailCover.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.scale_animation));
            detailPoster.setAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.fade_transition));
        }
    }
}