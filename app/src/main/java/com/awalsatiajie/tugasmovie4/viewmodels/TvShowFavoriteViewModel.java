package com.awalsatiajie.tugasmovie4.viewmodels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.awalsatiajie.tugasmovie4.model.DiscoverTvShow;
import com.awalsatiajie.tugasmovie4.model.TvShowFavorite;
import com.awalsatiajie.tugasmovie4.repository.TvShowFavoriteRepository;
import com.awalsatiajie.tugasmovie4.support.FavoriteInterface;
import com.awalsatiajie.tugasmovie4.support.FavoriteSupport;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class TvShowFavoriteViewModel extends AndroidViewModel implements FavoriteInterface {
    private MutableLiveData<ArrayList<DiscoverTvShow>> arrayListMutableLiveData = new MutableLiveData<>();
    private TvShowFavoriteRepository tvShowFavoriteRepository;

    public TvShowFavoriteViewModel(@NonNull Application application) {
        super(application);
        tvShowFavoriteRepository = new TvShowFavoriteRepository(application, this);
    }

    public void setTvShowFavorite(){
        tvShowFavoriteRepository.setTvShowFavList();
    }

    public MutableLiveData<ArrayList<DiscoverTvShow>> getArrayListMutableLiveData(){
        return arrayListMutableLiveData;
    }

    @Override
    public void setFavoriteData(FavoriteSupport favoriteData) {
        final List<TvShowFavorite> arrayList = favoriteData.getTvShowFavoriteList();
        ArrayList<DiscoverTvShow> discoverMovieArrayList = new ArrayList<>();

        Gson gson = new Gson();

        for(int i = 0; i < arrayList.size(); i++){
            Log.d("Array List", arrayList.get(i).getName());
            String data = gson.toJson(arrayList.get(i));

            try{
                DiscoverTvShow discoverMovie = new DiscoverTvShow(new JSONObject(data));
                discoverMovieArrayList.add(discoverMovie);

            }catch (Exception e){
                e.printStackTrace();
            }
        }

        arrayListMutableLiveData.postValue(discoverMovieArrayList);
    }
}
