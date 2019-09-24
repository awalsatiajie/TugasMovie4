package com.awalsatiajie.tugasmovie4.views;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
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
import com.awalsatiajie.tugasmovie4.api.TvShowDetailApiResponse;
import com.awalsatiajie.tugasmovie4.model.TvShow;
import com.awalsatiajie.tugasmovie4.model.TvShowFavorite;
import com.awalsatiajie.tugasmovie4.viewmodels.TvShowDetailViewModel;
import com.bumptech.glide.Glide;

public class TvShowDetailActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String EXTRA_DATA = "extra_data";
    private ImageView imgPoster, imgBackdrop;
    private TextView tvTitle, tvRelease, tvRate, tvOverview, textUserScore;
    private TextView textOverview, textRelease;
    private ProgressBar progressBar;
    private TvShow tvShow;
    private FloatingActionButton fabFavorite;
    private boolean status_favorite = false;
    private TvShowFavorite tvShowFavorite = new TvShowFavorite();
    private TvShowDetailViewModel tvShowDetailViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_show_detail);

        tvTitle = findViewById(R.id.tv_title);
        tvRelease = findViewById(R.id.tv_release);
        imgBackdrop = findViewById(R.id.img_back);
        imgPoster = findViewById(R.id.img_poster);
        progressBar = findViewById(R.id.progressBar);
        tvRate = findViewById(R.id.tv_user_score);
        tvOverview = findViewById(R.id.tv_overview);
        textUserScore = findViewById(R.id.text_user_score);
        textOverview = findViewById(R.id.text_overview);
        textRelease = findViewById(R.id.text_release_date);
        fabFavorite = findViewById(R.id.fab_favorite);

        setViewVisible(false);
        showFavoriteView(false);
        showLoading(true);

        if(getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        int i = getIntent().getIntExtra(EXTRA_DATA, 0);
        tvShowDetailViewModel = ViewModelProviders.of(this).get(TvShowDetailViewModel.class);
        tvShowDetailViewModel.getData(i).observe(this, getTvShowDetail);
        tvShowDetailViewModel.getTvShowFavoriteById(i);
    }

    private Observer<TvShowDetailApiResponse> getTvShowDetail = new Observer<TvShowDetailApiResponse>() {
        @Override
        public void onChanged(@Nullable TvShowDetailApiResponse tvShowDetailApiResponse) {
            if(tvShowDetailApiResponse == null){
                Toast.makeText(getApplicationContext(), "Something Error!", Toast.LENGTH_LONG).show();
            }

            if(tvShowDetailApiResponse.getThrowable() == null){
                tvShow = tvShowDetailApiResponse.getTvShow();
                setItemView();
                setViewVisible(true);
                showLoading(false);

            }else{
                Toast.makeText(getApplicationContext(), tvShowDetailApiResponse.getThrowable().getMessage(), Toast.LENGTH_LONG).show();
                showLoading(false);
            }
        }
    };

    public void showLoading(boolean loading){
        if(loading){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
        }
    }

    public void setViewVisible(boolean visible){
        if(visible){
            imgPoster.setVisibility(View.VISIBLE);
            textOverview.setVisibility(View.VISIBLE);
            textUserScore.setVisibility(View.VISIBLE);
            textRelease.setVisibility(View.VISIBLE);
            imgBackdrop.setVisibility(View.VISIBLE);
        }else{
            imgPoster.setVisibility(View.GONE);
            textOverview.setVisibility(View.GONE);
            textUserScore.setVisibility(View.GONE);
            textRelease.setVisibility(View.GONE);
            imgBackdrop.setVisibility(View.GONE);

        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        finish();

        return super.onOptionsItemSelected(menuItem);
    }

    private void setItemView(){
        if(getSupportActionBar() != null) getSupportActionBar().setTitle(tvShow.getName());

        tvRelease.setText(tvShow.getFirstAirDate());
        tvTitle.setText(tvShow.getName());
        tvOverview.setText(tvShow.getOverview());
        tvRate.setText(tvShow.getVoteAverage());

        Glide.with(getApplicationContext()).load(tvShow.getPosterURL()).into(imgPoster);
        Glide.with(getApplicationContext()).load(tvShow.getBackdropPath()).into(imgBackdrop);

        status_favorite = tvShowDetailViewModel.getStatus_favorite();
        showFavoriteView(true);

        tvShowFavorite.setId(tvShow.getId());
        tvShowFavorite.setFirst_air_date(tvShow.getFirstAirDate());
        tvShowFavorite.setName(tvShow.getName());
        tvShowFavorite.setOverview(tvShow.getOverview());
        tvShowFavorite.setPoster_path(tvShow.getPosterPath());

        fabFavorite.setOnClickListener(this);
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

    public void clickFavorite(TvShowFavorite tvShowFavorite){
        if(status_favorite){
            tvShowDetailViewModel.deleteTvShowFavorite(tvShowFavorite);
            setFavButtonIcon(false);
            Toast.makeText(this, getString(R.string.has_remove_tvshow_favorite), Toast.LENGTH_SHORT).show();
        }else{
            tvShowDetailViewModel.insertTvShowFavorite(tvShowFavorite);
            setFavButtonIcon(true);
            Toast.makeText(this, getString(R.string.has_add_tvshow_favorite), Toast.LENGTH_SHORT).show();
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
        switch (v.getId()){
            case R.id.fab_favorite:
                clickFavorite(tvShowFavorite);
                break;
        }
    }
}
