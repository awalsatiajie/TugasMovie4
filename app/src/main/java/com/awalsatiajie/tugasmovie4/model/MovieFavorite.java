package com.awalsatiajie.tugasmovie4.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import java.io.Serializable;

@Entity(tableName = "tbmoviefavorite")
public class MovieFavorite implements Serializable {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "title")
    private String title;

    @ColumnInfo(name = "overview")
    private String overview;

    @ColumnInfo(name = "poster_path")
    private String poster_path;

    @ColumnInfo(name = "release_date")
    private String release_date;

    @ColumnInfo(name = "date")
    private String date;

    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id = id;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public String getOverview(){
        return overview;
    }
    public void setOverview(String overview){
        this.overview = overview;
    }

    public String getPoster_path(){
        return poster_path;
    }
    public void setPoster_path(String poster_path){
        this.poster_path = poster_path;
    }

    public String getRelease_date(){
        return release_date;
    }
    public void setRelease_date(String release_date){
        this.release_date = release_date;
    }

    public String getDate(){
        return date;
    }
    public void setDate(String date){
        this.date = date;
    }
}
