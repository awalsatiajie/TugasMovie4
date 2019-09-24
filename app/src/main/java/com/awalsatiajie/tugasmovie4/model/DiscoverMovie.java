package com.awalsatiajie.tugasmovie4.model;

import com.awalsatiajie.tugasmovie4.BuildConfig;

import org.json.JSONException;
import org.json.JSONObject;

public class DiscoverMovie {

    private int id;
    private String title, overview, poster_path, date;

    public DiscoverMovie(JSONObject jsonObject){
        try {
            this.id =jsonObject.getInt("id");
            this.title = jsonObject.getString("title");
            this.overview = jsonObject.getString("overview");
            this.poster_path = jsonObject.getString("poster_path");
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getOverview(){
        return overview;
    }

    public String getPoster_path(){
        return poster_path;
    }

    public String getPosterUrl(){
        return BuildConfig.IMAGE_DB_URL + poster_path;
    }

    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }
}
