package com.awalsatiajie.tugasmovie4.repository;

import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.awalsatiajie.tugasmovie4.BuildConfig;
import com.awalsatiajie.tugasmovie4.api.MovieDbApiInterface;
import com.awalsatiajie.tugasmovie4.api.MovieDbApiService;
import com.awalsatiajie.tugasmovie4.api.TvShowDetailApiResponse;
import com.awalsatiajie.tugasmovie4.model.TvShow;
import com.awalsatiajie.tugasmovie4.support.FavoriteAsyncTask;
import com.awalsatiajie.tugasmovie4.support.FavoriteInterface;
import com.awalsatiajie.tugasmovie4.support.FavoriteSupport;
import com.awalsatiajie.tugasmovie4.support.MyAsyncCallback;
import com.awalsatiajie.tugasmovie4.database.appDatabase;

import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowDetailRepository implements MyAsyncCallback {
    private MovieDbApiInterface movieDbApiInterface;
    private appDatabase db;
    private WeakReference<FavoriteInterface> reference;

    public TvShowDetailRepository(Context context, FavoriteInterface favoriteInterface){
        this.movieDbApiInterface = MovieDbApiService.getClient();
        this.db = Room.databaseBuilder(context, appDatabase.class, "entertaimentdb").build();
        reference = new WeakReference<>(favoriteInterface);
    }

    public MutableLiveData<TvShowDetailApiResponse> getTvShowDetail(int id){
        final MutableLiveData<TvShowDetailApiResponse> mutableLiveData = new MutableLiveData<>();
        final Map<String, String> map = new HashMap<>();
        map.put("api_key", BuildConfig.MY_API_KEY);
        map.put("language", "en-US");

        Call<TvShow> tvShowCall = movieDbApiInterface.getTvShowDetail(id, map);
        tvShowCall.enqueue(new Callback<TvShow>() {
            @Override
            public void onResponse(Call<TvShow> call, Response<TvShow> response) {
                if(response.isSuccessful()){
                    mutableLiveData.postValue(new TvShowDetailApiResponse(response.body()));
                }
            }

            @Override
            public void onFailure(Call<TvShow> call, Throwable t) {
                mutableLiveData.postValue(new TvShowDetailApiResponse(t));
            }
        });

        return mutableLiveData;
    }

    public void executeFavoriteData(FavoriteSupport favoriteSupport){
        FavoriteAsyncTask favoriteAsyncTask = new FavoriteAsyncTask(this, db);
        favoriteAsyncTask.execute(favoriteSupport);
    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public void onPostExecute(FavoriteSupport favoriteSupport) {
        reference.get().setFavoriteData(favoriteSupport);
    }
}
