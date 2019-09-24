package com.awalsatiajie.tugasmovie4.repository;

import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.content.Context;

import com.awalsatiajie.tugasmovie4.BuildConfig;
import com.awalsatiajie.tugasmovie4.api.MovieDbApiInterface;
import com.awalsatiajie.tugasmovie4.api.MovieDbApiService;
import com.awalsatiajie.tugasmovie4.api.MovieDetailApiResponse;
import com.awalsatiajie.tugasmovie4.model.Movie;
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

public class MovieDetailRepository implements MyAsyncCallback {
    private MovieDbApiInterface movieDbApiInterface;
    private appDatabase db;
    private WeakReference<FavoriteInterface> reference;

    public MovieDetailRepository(Context context, FavoriteInterface favoriteInterface) {
        this.db = Room.databaseBuilder(context, appDatabase.class, "entertaimentdb").build();
        reference = new WeakReference<>(favoriteInterface);

        this.movieDbApiInterface = MovieDbApiService.getClient();
    }

    public MutableLiveData<MovieDetailApiResponse> getMovieDetail(int id){
        final MutableLiveData<MovieDetailApiResponse> mutableLiveData = new MutableLiveData<>();
        final Map<String, String> map = new HashMap<>();
        map.put("api_key", BuildConfig.MY_API_KEY);
        map.put("language", "en-US");

        Call<Movie> movieCall = movieDbApiInterface.getMovieDetail(id, map);
        movieCall.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if(response.isSuccessful()){
                    mutableLiveData.postValue(new MovieDetailApiResponse(response.body()));
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                mutableLiveData.postValue(new MovieDetailApiResponse(t));
            }
        });

        return mutableLiveData;
    }

    public void executeFavoriteData(FavoriteSupport favoriteData){
        FavoriteAsyncTask favoriteAsyncTask = new FavoriteAsyncTask(this, db);
        favoriteAsyncTask.execute(favoriteData);
    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public void onPostExecute(FavoriteSupport favoriteSupport) {
        reference.get().setFavoriteData(favoriteSupport);
    }
}
