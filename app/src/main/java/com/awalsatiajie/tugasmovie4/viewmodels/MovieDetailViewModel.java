package com.awalsatiajie.tugasmovie4.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.awalsatiajie.tugasmovie4.api.MovieDbApiInterface;
import com.awalsatiajie.tugasmovie4.api.MovieDetailApiResponse;
import com.awalsatiajie.tugasmovie4.model.MovieFavorite;
import com.awalsatiajie.tugasmovie4.repository.MovieDetailRepository;
import com.awalsatiajie.tugasmovie4.support.FavoriteInterface;
import com.awalsatiajie.tugasmovie4.support.FavoriteSupport;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MovieDetailViewModel extends AndroidViewModel implements FavoriteInterface {
    private MediatorLiveData<MovieDetailApiResponse> movieDetailApiResponseMediatorLiveData = new MediatorLiveData<>();
    private MovieDetailRepository movieDetailRepository;
    private boolean  status_favorite = true;

    public MovieDetailViewModel(@NonNull Application application) {
        super(application);
        movieDetailRepository = new MovieDetailRepository(application, this);
    }

    public LiveData<MovieDetailApiResponse> getData(int id){
        movieDetailApiResponseMediatorLiveData
                .addSource(movieDetailRepository.getMovieDetail(id), new Observer<MovieDetailApiResponse>() {
                    @Override
                    public void onChanged(@Nullable MovieDetailApiResponse movieDetailApiResponse) {
                        movieDetailApiResponseMediatorLiveData.setValue(movieDetailApiResponse);
                    }
                });

        return movieDetailApiResponseMediatorLiveData;
    }

    public void insertFavoriteMovie(MovieFavorite movieFavorite){
        movieFavorite.setDate(getCurrentDate());
        FavoriteSupport favoriteSupport = new FavoriteSupport(202, movieFavorite);
        movieDetailRepository.executeFavoriteData(favoriteSupport);
    }

    public void deleteFavoriteMovie(MovieFavorite movieFavorite){
        FavoriteSupport favoriteSupport = new FavoriteSupport(203);
        favoriteSupport.setMovieFavorite(movieFavorite);
        movieDetailRepository.executeFavoriteData(favoriteSupport);
    }

    public void getFavoriteMovie(int id){
        FavoriteSupport favoriteSupport = new FavoriteSupport(204);
        Log.d("ID", String.valueOf(id));
        favoriteSupport.setItem_id(id);
        movieDetailRepository.executeFavoriteData(favoriteSupport);
    }

    public boolean getStatus_favorite() {
        return status_favorite;
    }

    @Override
    public void setFavoriteData(FavoriteSupport favoriteData) {
        if(favoriteData.getStatusRequest() == 204){
            if(favoriteData.getMovieFavorite() == null){
                status_favorite = false;
            }
        }
    }

    private String getCurrentDate(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();

        return dateFormat.format(date);
    }
}
