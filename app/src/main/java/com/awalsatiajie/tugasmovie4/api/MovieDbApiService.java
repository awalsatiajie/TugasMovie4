package com.awalsatiajie.tugasmovie4.api;

import com.awalsatiajie.tugasmovie4.BuildConfig;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDbApiService {
    private static Retrofit retrofit = null;
    private static final String BASE_URL = BuildConfig.MOVIE_DB_URL;

    public static MovieDbApiInterface getClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(MovieDbApiInterface.class);
    }
}
