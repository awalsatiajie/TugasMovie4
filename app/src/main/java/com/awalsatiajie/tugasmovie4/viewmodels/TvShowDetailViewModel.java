package com.awalsatiajie.tugasmovie4.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.awalsatiajie.tugasmovie4.api.TvShowDetailApiResponse;
import com.awalsatiajie.tugasmovie4.model.TvShowFavorite;
import com.awalsatiajie.tugasmovie4.repository.TvShowDetailRepository;
import com.awalsatiajie.tugasmovie4.support.FavoriteInterface;
import com.awalsatiajie.tugasmovie4.support.FavoriteSupport;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TvShowDetailViewModel extends AndroidViewModel implements FavoriteInterface {
    private MediatorLiveData<TvShowDetailApiResponse> mediatorLiveData = new MediatorLiveData<>();
    private TvShowDetailRepository tvShowDetailRepository;
    private boolean status_favorite = true;

    public TvShowDetailViewModel(@NonNull Application application) {
        super(application);
        tvShowDetailRepository = new TvShowDetailRepository(application, this);
    }

    public LiveData<TvShowDetailApiResponse> getData(int id){
        mediatorLiveData.addSource(tvShowDetailRepository.getTvShowDetail(id), new Observer<TvShowDetailApiResponse>() {
            @Override
            public void onChanged(@Nullable TvShowDetailApiResponse tvShowDetailApiResponse) {
                mediatorLiveData.setValue(tvShowDetailApiResponse);
            }
        });

        return mediatorLiveData;
    }

    public void insertTvShowFavorite(TvShowFavorite tvShowFavorite){
        FavoriteSupport fsupport = new FavoriteSupport(212, tvShowFavorite);
        tvShowDetailRepository.executeFavoriteData(fsupport);
    }

    public void deleteTvShowFavorite(TvShowFavorite tvShowFavorite){
        tvShowFavorite.setDate(getCurrentDate());
        FavoriteSupport favoriteSupport = new FavoriteSupport(213);
        favoriteSupport.setTvShowFavorite(tvShowFavorite);
        tvShowDetailRepository.executeFavoriteData(favoriteSupport);
    }

    public void getTvShowFavoriteById(int id){
        FavoriteSupport favoriteSupport = new FavoriteSupport(214);
        favoriteSupport.setItem_id(id);

        tvShowDetailRepository.executeFavoriteData(favoriteSupport);
    }

    public boolean getStatus_favorite() {
        return status_favorite;
    }

    @Override
    public void setFavoriteData(FavoriteSupport favoriteData) {
        if(favoriteData.getStatusRequest() == 214){
            if(favoriteData.getTvShowFavorite() == null){
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
