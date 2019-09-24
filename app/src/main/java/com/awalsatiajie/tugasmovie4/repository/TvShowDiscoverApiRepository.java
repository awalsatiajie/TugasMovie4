package com.awalsatiajie.tugasmovie4.repository;

import android.arch.lifecycle.MutableLiveData;

import com.awalsatiajie.tugasmovie4.BuildConfig;
import com.awalsatiajie.tugasmovie4.api.JsonApiResponse;
import com.awalsatiajie.tugasmovie4.api.MovieDbApiInterface;
import com.awalsatiajie.tugasmovie4.api.MovieDbApiService;

import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TvShowDiscoverApiRepository {
    private MovieDbApiInterface movieDbApiInterface;

    public TvShowDiscoverApiRepository(){
        movieDbApiInterface = MovieDbApiService.getClient();
    }

    public MutableLiveData<JsonApiResponse> getDiscoverTvShow(){
        final MutableLiveData<JsonApiResponse> apiResponseMutableLiveData = new MutableLiveData<>();
        Map<String, String> map = new HashMap<>();
        map.put("api_key", BuildConfig.MY_API_KEY);
        map.put("language", "en-US");

        Call<ResponseBody> responseBodyCall =  movieDbApiInterface.getDiscoverTvShowJson(map);
        responseBodyCall.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    apiResponseMutableLiveData.postValue(new JsonApiResponse(response.body()));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                apiResponseMutableLiveData.postValue(new JsonApiResponse(t));
            }
        });

        return apiResponseMutableLiveData;

    }
}
