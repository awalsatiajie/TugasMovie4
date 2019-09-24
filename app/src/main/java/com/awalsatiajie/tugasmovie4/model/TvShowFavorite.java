package com.awalsatiajie.tugasmovie4.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "tbtvshowfavorite")
public class TvShowFavorite implements Serializable {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "overview")
    private String overview;

    @ColumnInfo(name = "first_air_date")
    private String first_air_date;

    @ColumnInfo(name = "poster_path")
    private String poster_path;

    @ColumnInfo(name = "date")
    private String date;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }

    public String getOverview(){
        return overview;
    }
    public void setOverview(String overview){
        this.overview = overview;
    }

    public String getFirst_air_date(){
        return first_air_date;
    }
    public void setFirst_air_date(String first_air_date){
        this.first_air_date = first_air_date;
    }

    public String getPoster_path(){
        return poster_path;
    }
    public void setPoster_path(String poster_path){
        this.poster_path = poster_path;
    }

    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }
}
