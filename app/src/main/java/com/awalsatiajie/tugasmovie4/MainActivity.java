package com.awalsatiajie.tugasmovie4;

import android.content.Intent;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.awalsatiajie.tugasmovie4.views.MovieFavoriteFragment;
import com.awalsatiajie.tugasmovie4.views.MovieFragment;
import com.awalsatiajie.tugasmovie4.views.TvShowFavoriteFragment;
import com.awalsatiajie.tugasmovie4.views.TvShowFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;

            switch (item.getItemId()){
                case R.id.bottomnav_movie:
                    fragment = new MovieFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();
                    return true;
                case R.id.bottomnav_tvshow:
                    fragment = new TvShowFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();
                    return true;
                case R.id.bottomnav_movie_favorite:
                    fragment = new MovieFavoriteFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();
                    return true;
                case R.id.bottomnav_tvshow_favorite:
                    fragment = new TvShowFavoriteFragment();
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, fragment, fragment.getClass().getSimpleName())
                            .commit();
                    return true;
            }

            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = findViewById(R.id.bottom_nav);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        if(savedInstanceState == null){
            Fragment fragment = new MovieFragment();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, fragment, fragment.getClass().getSimpleName())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem){
        setMode(menuItem.getItemId());
        return super.onOptionsItemSelected(menuItem);
    }

    private void showAboutMe(){
        Intent i =new Intent(getApplicationContext(),AboutActivity.class);
        startActivity(i);
    }

    private void bahasa(){
        Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
        startActivity(intent);
    }

    public void setMode(int selectedMode) {
        switch (selectedMode) {
            case R.id.about_me:
                showAboutMe();
                break;
            case R.id.menu_locale:
                bahasa();
                break;
        }
    }
}
