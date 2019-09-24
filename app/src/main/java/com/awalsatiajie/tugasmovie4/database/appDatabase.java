package com.awalsatiajie.tugasmovie4.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.awalsatiajie.tugasmovie4.dao.MovieFavoriteDAO;
import com.awalsatiajie.tugasmovie4.dao.TvShowFavoriteDAO;
import com.awalsatiajie.tugasmovie4.model.MovieFavorite;
import com.awalsatiajie.tugasmovie4.model.TvShowFavorite;

@Database(entities = {MovieFavorite.class, TvShowFavorite.class}, version = 1)
public abstract class appDatabase extends RoomDatabase {
    public abstract MovieFavoriteDAO movieFavoriteDAO();
    public abstract TvShowFavoriteDAO tvShowFavoriteDAO();
}
