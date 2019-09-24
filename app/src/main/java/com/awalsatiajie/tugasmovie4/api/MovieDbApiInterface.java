package com.awalsatiajie.tugasmovie4.api;

import com.awalsatiajie.tugasmovie4.model.Movie;
import com.awalsatiajie.tugasmovie4.model.TvShow;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

public interface MovieDbApiInterface {
    @GET("discover/movie")
    Call<ResponseBody> getDiscoverMovieJson(@QueryMap Map<String, String> options);

    @GET("discover/tv")
    Call<ResponseBody> getDiscoverTvShowJson(@QueryMap Map<String, String> options);

    @GET("movie/{id}")
    Call<Movie> getMovieDetail(@Path("id") int id, @QueryMap Map<String, String> options);

    @GET("tv/{id}")
    Call<TvShow> getTvShowDetail(@Path("id") int id, @QueryMap Map<String, String> options);

}
