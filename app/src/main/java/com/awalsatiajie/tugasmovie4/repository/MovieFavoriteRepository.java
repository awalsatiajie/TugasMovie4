package com.awalsatiajie.tugasmovie4.repository;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.awalsatiajie.tugasmovie4.support.FavoriteAsyncTask;
import com.awalsatiajie.tugasmovie4.support.FavoriteInterface;
import com.awalsatiajie.tugasmovie4.support.FavoriteSupport;
import com.awalsatiajie.tugasmovie4.support.MyAsyncCallback;
import com.awalsatiajie.tugasmovie4.database.appDatabase;

import java.lang.ref.WeakReference;

public class MovieFavoriteRepository implements MyAsyncCallback {
    private FavoriteSupport support;
    private appDatabase db;
    private WeakReference<FavoriteInterface> reference;

    public MovieFavoriteRepository(Context context, FavoriteInterface favoriteInterface){
        db = Room.databaseBuilder(context, appDatabase.class, "entertaimentdb")
                .allowMainThreadQueries().build();
        reference = new WeakReference<>(favoriteInterface);
    }

    public void setMovieFavList(){
        FavoriteAsyncTask favoriteAsyncTask = new FavoriteAsyncTask(this, db);
        FavoriteSupport favoriteSupport = new FavoriteSupport(201);

        favoriteAsyncTask.execute(favoriteSupport);
    }

    public FavoriteSupport getSupport() {
        return support;
    }

    @Override
    public void onPreExecute() {

    }

    @Override
    public void onPostExecute(FavoriteSupport favoriteSupport) {
        reference.get().setFavoriteData(favoriteSupport);
        this.support = favoriteSupport;
    }
}
