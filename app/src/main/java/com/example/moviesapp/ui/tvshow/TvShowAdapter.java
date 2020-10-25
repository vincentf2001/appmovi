package com.example.moviesapp.ui.tvshow;

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
import com.example.moviesapp.model.local.TvShow;
import com.example.moviesapp.util.Constants;

import java.util.List;

public class TvShowAdapter extends RecyclerView.Adapter<TvShowAdapter.ViewHolder> {

    private Context context;
    private List<TvShow> showData;

    public TvShowAdapter(Context context) {
        this.context = context;
    }

    public void setShowData(List<TvShow> showData) {
        this.showData = showData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TvShow tv = showData.get(position);
        Glide.with(context)
                .load(Constants.BaseSetting.BASE_IMAGE_URL + tv.getCover())
                .into(holder.cover);
        holder.title.setText(tv.getTitle());
        holder.popular.setText(tv.getPopularity());
        holder.date.setText(tv.getReleaseDate());
        holder.itemView.setOnClickListener(view -> {
            TvShowFragmentDirections.ActionTvDetail action = TvShowFragmentDirections.actionTvDetail(null, tv);
            Navigation.findNavController(view).navigate(action);
        });
    }

    @Override
    public int getItemCount() {
        return showData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView cover;
        TextView title, popular, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cover = itemView.findViewById(R.id.item_cover);
            title = itemView.findViewById(R.id.item_title);
            popular = itemView.findViewById(R.id.item_popular);
            date = itemView.findViewById(R.id.item_date);
        }
    }
}
