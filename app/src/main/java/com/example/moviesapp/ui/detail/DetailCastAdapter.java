package com.example.moviesapp.ui.detail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapp.R;
import com.example.moviesapp.model.local.Cast;
import com.example.moviesapp.util.Constants;

import java.util.List;

public class DetailCastAdapter extends RecyclerView.Adapter<DetailCastAdapter.ViewHolder> {

    private Context context;
    private List<Cast> castData;

    public DetailCastAdapter(Context context) {
        this.context = context;
    }

    public void setCastData(List<Cast> castData) {
        this.castData = castData;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cast, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cast cast = castData.get(position);
        Glide.with(context)
                .load(Constants.BaseSetting.BASE_IMAGE_URL + cast.getImg_url())
                .into(holder.iv_cast);
        holder.tv_name.setText(cast.getName());
        holder.tv_role.setText(cast.getRole());
    }

    @Override
    public int getItemCount() {
        return castData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_cast;
        TextView tv_name, tv_role;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_cast = itemView.findViewById(R.id.cast_img);
            tv_name = itemView.findViewById(R.id.cast_name);
            tv_role = itemView.findViewById(R.id.cast_role);
        }
    }
}
