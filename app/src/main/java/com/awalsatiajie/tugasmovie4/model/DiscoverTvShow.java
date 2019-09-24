package com.awalsatiajie.tugasmovie4.model;

import com.awalsatiajie.tugasmovie4.BuildConfig;

import org.json.JSONException;
import org.json.JSONObject;

public class DiscoverTvShow {

    private int id;
    private String name, overview, poster_path, date;

    public DiscoverTvShow(JSONObject jsonObject){
        try {
            this.id = jsonObject.getInt("id");
            this.name = jsonObject.getString("name");
            this.overview = jsonObject.getString("overview");
            this.poster_path = jsonObject.getString("poster_path");
        }catch (JSONException e){
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }
    public String getPosterURL(){
        return BuildConfig.IMAGE_DB_URL + poster_path;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
}
