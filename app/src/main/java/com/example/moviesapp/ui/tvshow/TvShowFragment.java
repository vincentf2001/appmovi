package com.example.moviesapp.ui.tvshow;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.moviesapp.R;
import com.example.moviesapp.model.local.TvShow;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TvShowFragment extends Fragment {

    @BindView(R.id.progressBarTv)
    ProgressBar loading;
    @BindView(R.id.rv_show)
    RecyclerView rvShow;
    @BindView(R.id.listShowError)
    TextView tvError;

    private TvShowViewModel viewModel;
    private TvShowAdapter adapter;

    public TvShowFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tv_show, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        showLoading(true);
        viewModel = ViewModelProviders.of(requireActivity()).get(TvShowViewModel.class);
        viewModel.getShows().observe(requireActivity(), observeViewModel);

        rvShow.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new TvShowAdapter(getActivity());
    }

    private Observer<List<TvShow>> observeViewModel = tvShows -> {
        if (tvShows != null) {
            adapter.setShowData(tvShows);
            adapter.notifyDataSetChanged();
            rvShow.setAdapter(adapter);
            showLoading(false);
        }
    };

    private void showLoading(Boolean state) {
        if (state) {
            rvShow.setVisibility(View.GONE);
            tvError.setVisibility(View.GONE);
            loading.setVisibility(View.VISIBLE);
        } else {
            rvShow.setVisibility(View.VISIBLE);
            loading.setVisibility(View.GONE);
        }
    }
}