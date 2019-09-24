package com.awalsatiajie.tugasmovie4.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.awalsatiajie.tugasmovie4.model.MovieFavorite;

import java.util.List;

@Dao
public interface MovieFavoriteDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertMovieFav (MovieFavorite movieFavorite);

    @Query("SELECT * FROM tbmoviefavorite ORDER BY 'date' DESC")
    List<MovieFavorite> getMovieFavorite();

    @Query("SELECT id FROM tbmoviefavorite WHERE id = :id LIMIT 1")
    MovieFavorite getMovieFavoriteById(int id);

    @Delete
    int deleteMovieFavorite (MovieFavorite movieFavorite);
}
