package com.example.moviesapp.ui.movie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.R;
import com.example.moviesapp.model.local.Movie;
import com.example.moviesapp.util.Constants;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {

    private Context context;
    private List<Movie> moviesData;

    public MovieAdapter(Context context) {
        this.context = context;
    }

    public void setMovies(List<Movie> movies) {
        this.moviesData = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, viewGroup, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder movieViewHolder, int i) {
        Movie m = moviesData.get(i);
        Glide.with(context)
                .load(Constants.BaseSetting.BASE_IMAGE_URL + m.getCover())
                .into(movieViewHolder.movie_cover);
        movieViewHolder.movie_title.setText(m.getTitle());
        movieViewHolder.movie_popular.setText(m.getPopularity());
        movieViewHolder.movie_date.setText(m.getReleaseDate());
        movieViewHolder.itemView.setOnClickListener(view -> {
            MovieFragmentDirections.ActionMovieDetail action = MovieFragmentDirections.actionMovieDetail(m, null);
            Navigation.findNavController(view).navigate(action);
        });
    }

    @Override
    public int getItemCount() {
        return moviesData.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        ImageView movie_cover;
        TextView movie_title, movie_popular, movie_date;

        MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_cover = itemView.findViewById(R.id.item_cover);
            movie_title = itemView.findViewById(R.id.item_title);
            movie_popular = itemView.findViewById(R.id.item_popular);
            movie_date = itemView.findViewById(R.id.item_date);
        }
    }
}
