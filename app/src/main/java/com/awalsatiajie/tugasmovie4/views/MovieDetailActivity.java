package com.awalsatiajie.tugasmovie4.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.awalsatiajie.tugasmovie4.R;
import com.awalsatiajie.tugasmovie4.api.MovieDetailApiResponse;
import com.awalsatiajie.tugasmovie4.model.Movie;
import com.awalsatiajie.tugasmovie4.model.MovieFavorite;
import com.awalsatiajie.tugasmovie4.viewmodels.MovieDetailViewModel;
import com.bumptech.glide.Glide;

public class MovieDetailActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_DATA = "extra_data";
    private ImageView imgPoster, imgBakdrop;
    private TextView tvTitle, tvRelease, tvRate, tvOverview, textUserScore, textOverview, textRelease;
    private ProgressBar progressBar;
    private Movie movie;
    private FloatingActionButton fabFavorite;
    private MovieDetailViewModel movieDetailViewModel;
    private MovieFavorite movieFavorite = new MovieFavorite();
    private boolean status_favorite = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        progressBar = findViewById(R.id.progressBar);
        imgPoster = findViewById(R.id.img_poster);
        imgBakdrop = findViewById(R.id.img_back);
        tvTitle = findViewById(R.id.tv_title);
        tvRelease = findViewById(R.id.tv_release);
        tvOverview = findViewById(R.id.tv_overview);
        tvRate = findViewById(R.id.tv_user_score);
        textUserScore = findViewById(R.id.text_user_score);
        textOverview = findViewById(R.id.text_overview);
        textRelease = findViewById(R.id.text_release_date);
        fabFavorite = findViewById(R.id.fab_favorite);

        setViewVisible(false);
        showFavoriteView(false);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int filmId = getIntent().getIntExtra(EXTRA_DATA, 0);
        movieDetailViewModel = ViewModelProviders.of(this).get(MovieDetailViewModel.class);
        movieDetailViewModel.getData(filmId).observe(this, getMovieDetail);
        movieDetailViewModel.getFavoriteMovie(filmId);
    }

    private Observer<MovieDetailApiResponse> getMovieDetail = new Observer<MovieDetailApiResponse>() {
        @Override
        public void onChanged(@Nullable MovieDetailApiResponse data) {
            if (data == null) {
                Toast.makeText(getApplicationContext(), data.getThrowable().getMessage(), Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }

            if(data.getThrowable() == null){
                movie = data.getMovie();
                setView();
                setViewVisible(true);
            }else{
                Toast.makeText(getApplicationContext(), data.getThrowable().getMessage(), Toast.LENGTH_LONG).show();
                progressBar.setVisibility(View.GONE);
            }
        }
    };

    private void setViewVisible(boolean b){
        if(b){
            textOverview.setVisibility(View.VISIBLE);
            imgPoster.setVisibility(View.VISIBLE);
            textUserScore.setVisibility(View.VISIBLE);
            textRelease.setVisibility(View.VISIBLE);
            imgBakdrop.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.GONE);
        }else{
            textOverview.setVisibility(View.GONE);
            imgPoster.setVisibility(View.GONE);
            textUserScore.setVisibility(View.GONE);
            imgBakdrop.setVisibility(View.GONE);
            textRelease.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
        }
    }

    private void setView(){
        if(getSupportActionBar() != null) getSupportActionBar().setTitle(movie.getTitle());


        tvRelease.setText(movie.getReleaseDate());
        tvTitle.setText(movie.getTitle());
        tvOverview.setText(movie.getOverview());
        tvRate.setText(movie.getVoteAverage());

        Glide.with(this).load(movie.getBackdrop_path()).into(imgBakdrop);
        Glide.with(this).load(movie.getPosterURl()).into(imgPoster);

        status_favorite =  movieDetailViewModel.getStatus_favorite();
        showFavoriteView(true);

        movieFavorite.setId(movie.getId());
        movieFavorite.setTitle(movie.getTitle());
        movieFavorite.setOverview(movie.getOverview());
        movieFavorite.setPoster_path(movie.getPosterPath());
        movieFavorite.setRelease_date(movie.getReleaseDate());

        fabFavorite.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        finish();

        return super.onOptionsItemSelected(menuItem);
    }

    private void showFavoriteView(boolean b){
        if(b){
            if(status_favorite){
                setFavButtonIcon(true);
            }else{
                setFavButtonIcon(false);
            }

            fabFavorite.show();
            fabFavorite.setClickable(true);

        }else{
            fabFavorite.hide();
        }
    }

    private void clickFavorite(MovieFavorite movieFavorite){
        if(status_favorite){
            movieDetailViewModel.deleteFavoriteMovie(movieFavorite);
            setFavButtonIcon(false);
            Toast.makeText(this, getString(R.string.has_remove_movie_favorite), Toast.LENGTH_SHORT).show();
        }else{
            movieDetailViewModel.insertFavoriteMovie(movieFavorite);
            setFavButtonIcon(true);
            Toast.makeText(this, getString(R.string.has_add_movie_favorite), Toast.LENGTH_SHORT).show();
            status_favorite = true;
        }
    }

    private void setFavButtonIcon(boolean b){
        if(b){
            fabFavorite.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_black_24dp));
        }else{
            fabFavorite.setImageDrawable(getResources().getDrawable(R.drawable.ic_favorite_border_black_24dp));
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.fab_favorite){
            clickFavorite(movieFavorite);
        }
    }
}
