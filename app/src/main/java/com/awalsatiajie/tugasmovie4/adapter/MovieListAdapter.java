package com.awalsatiajie.tugasmovie4.adapter;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.awalsatiajie.tugasmovie4.R;
import com.awalsatiajie.tugasmovie4.model.DiscoverMovie;
import com.awalsatiajie.tugasmovie4.model.MovieFavorite;
import com.awalsatiajie.tugasmovie4.support.CustomOnClickListener;
import com.awalsatiajie.tugasmovie4.viewmodels.MovieDetailViewModel;
import com.awalsatiajie.tugasmovie4.views.MovieDetailActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.ViewHolder> {
    private ArrayList<DiscoverMovie> discoverMovies = new ArrayList<>();
    private final Context context;
    private MovieDetailViewModel movieDetailViewModel;

    public MovieListAdapter(Context context) {
        this.context = context;
        movieDetailViewModel = new MovieDetailViewModel((Application) context.getApplicationContext());
    }

    public void setDiscoverMovies(ArrayList<DiscoverMovie> discoverMovies) {
        this.discoverMovies = discoverMovies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_row, viewGroup, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(discoverMovies.get(i));
        viewHolder.cvItem.setOnClickListener(new CustomOnClickListener(i, new CustomOnClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View v, int position) {
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra(MovieDetailActivity.EXTRA_DATA, discoverMovies.get(position).getId());
                context.startActivity(intent);
            }
        }));

    }

    @Override
    public int getItemCount() {
        return discoverMovies.size();
    }

    public DiscoverMovie getData(int i){
        return discoverMovies.get(i);
    }

    public void removeItem(int position, DiscoverMovie discoverMovie){
        MovieFavorite movieFavorite = new MovieFavorite();

        movieFavorite.setDate(discoverMovie.getDate());
        movieFavorite.setTitle(discoverMovie.getTitle());
        movieFavorite.setId(discoverMovie.getId());
        movieFavorite.setPoster_path(discoverMovie.getPoster_path());
        movieFavorite.setOverview(discoverMovie.getOverview());

        movieDetailViewModel.deleteFavoriteMovie(movieFavorite);
        discoverMovies.remove(position);
        notifyItemRemoved(position);
    }

    public void restoreItem(DiscoverMovie discoverMovie,int position){
        MovieFavorite movieFavorite = new MovieFavorite();

        movieFavorite.setDate(discoverMovie.getDate());
        movieFavorite.setTitle(discoverMovie.getTitle());
        movieFavorite.setId(discoverMovie.getId());
        movieFavorite.setPoster_path(discoverMovie.getPoster_path());
        movieFavorite.setOverview(discoverMovie.getOverview());

        movieDetailViewModel.insertFavoriteMovie(movieFavorite);
        discoverMovies.add(position, discoverMovie);
        notifyItemInserted(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle, tvOverview;
        private ImageView imagePoster;
        public CardView cvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvOverview = itemView.findViewById(R.id.tv_overview);
            imagePoster = itemView.findViewById(R.id.img_poster);
            cvItem = itemView.findViewById(R.id.cv_item);
        }

        private void bind(DiscoverMovie discoverMovie){
            tvTitle.setText(discoverMovie.getTitle());
            tvOverview.setText(discoverMovie.getOverview());

            Glide.with(context).load(discoverMovie.getPosterUrl()).into(imagePoster);
        }
    }
}
