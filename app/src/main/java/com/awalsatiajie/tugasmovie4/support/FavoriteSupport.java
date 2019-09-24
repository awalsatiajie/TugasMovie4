package com.awalsatiajie.tugasmovie4.support;

import com.awalsatiajie.tugasmovie4.model.MovieFavorite;
import com.awalsatiajie.tugasmovie4.model.TvShowFavorite;

import java.util.List;

public class FavoriteSupport {
    private int STATUS_REQUEST, item_id;

    private MovieFavorite movieFavorite;
    private List<MovieFavorite> movieFavoriteList;
    private TvShowFavorite tvShowFavorite;
    private List<TvShowFavorite> tvShowFavoriteList;

    public FavoriteSupport(int i, MovieFavorite movieFavorite){
        this.STATUS_REQUEST = i;
        this.movieFavorite = movieFavorite;
    }

    public FavoriteSupport(int i, TvShowFavorite tvShowFavorite){
        this.STATUS_REQUEST = i;
        this.tvShowFavorite = tvShowFavorite;
    }

    public FavoriteSupport(int i){
        this.STATUS_REQUEST = i;
    }

    public int getStatusRequest(){
        return STATUS_REQUEST;
    }

    public MovieFavorite getMovieFavorite(){
        return movieFavorite;
    }

    public List<MovieFavorite> getMovieFavoriteList(){
        return movieFavoriteList;
    }

    public void setMovieFavoriteList(List<MovieFavorite> movieFavoriteList){
        this.movieFavoriteList = movieFavoriteList;
    }

    public void  setMovieFavorite(MovieFavorite movieFavorite){
        this.movieFavorite = movieFavorite;
    }

    public void setStatusInsert(Long status){
        Long insert_status = status;
    }

    public void setItem_id(int item_id){
        this.item_id = item_id;
    }

    public int getItem_id(){
        return item_id;
    }

    public void setDeleteStatus(int delete_status){
        int delete_status1= delete_status;
    }

    public TvShowFavorite getTvShowFavorite() {
        return tvShowFavorite;
    }

    public void setTvShowFavorite(TvShowFavorite tvShowFavorite) {
        this.tvShowFavorite = tvShowFavorite;
    }

    public List<TvShowFavorite> getTvShowFavoriteList(){
        return tvShowFavoriteList;
    }

    public void setTvShowFavoriteList(List<TvShowFavorite> tvShowFavoriteList) {
        this.tvShowFavoriteList = tvShowFavoriteList;
    }
}
