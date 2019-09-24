package com.awalsatiajie.tugasmovie4.api;

import com.awalsatiajie.tugasmovie4.model.TvShow;

public class TvShowDetailApiResponse {
    private TvShow tvShow;
    private Throwable throwable;

    public TvShowDetailApiResponse(TvShow tvShow){
        this.tvShow = tvShow;
        this.throwable = null;
    }

    public TvShowDetailApiResponse(Throwable throwable){
        this.throwable = throwable;
        this.tvShow = null;
    }

    public TvShow getTvShow() {
        return tvShow;
    }

    public Throwable getThrowable() {
        return throwable;
    }
}
