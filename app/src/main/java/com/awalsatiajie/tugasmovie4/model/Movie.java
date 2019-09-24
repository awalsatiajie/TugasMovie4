package com.awalsatiajie.tugasmovie4.model;

import com.awalsatiajie.tugasmovie4.BuildConfig;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Movie {
    @SerializedName("backdrop_path")
    @Expose
    private String backdrop_path;

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("overview")
    @Expose
    private String overview;

    @SerializedName("poster_path")
    @Expose
    private String posterPath;

    @SerializedName("release_date")
    @Expose
    private String releaseDate;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("vote_average")
    @Expose
    private Double voteAverage;

    public String getBackdrop_path(){
        return BuildConfig.IMAGE_DB_URL + backdrop_path;
    }

    public int getId(){
        return id;
    }

    public String getOverview(){
        return overview;
    }

    public String getPosterPath(){
        return posterPath;
    }
    public String getPosterURl(){
        return BuildConfig.IMAGE_DB_URL + posterPath;
    }

    public String getReleaseDate(){
        return releaseDate;
    }

    public String getTitle(){
        return title;
    }

    public String getVoteAverage(){
        return voteAverage.toString();
    }
}
