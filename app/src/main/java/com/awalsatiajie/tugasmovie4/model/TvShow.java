package com.awalsatiajie.tugasmovie4.model;

import com.awalsatiajie.tugasmovie4.BuildConfig;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TvShow {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("backdrop_path")
    @Expose
    private String backdropPath;

    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("first_air_date")
    @Expose
    private String firstAirDate;

    @SerializedName("last_air_date")
    @Expose
    private String lastAirDate;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;

    public String getBackdropPath(){
        return "https://image.tmdb.org/t/p/w500" + backdropPath;
    }

    public String getFirstAirDate() {
        return firstAirDate;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getPosterURL() {
        return BuildConfig.IMAGE_DB_URL +  posterPath;
    }

    public String getVoteAverage() {
        return voteAverage.toString();
    }
}
