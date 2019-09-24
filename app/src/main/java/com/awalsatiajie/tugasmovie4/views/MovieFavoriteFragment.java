package com.awalsatiajie.tugasmovie4.views;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.awalsatiajie.tugasmovie4.R;
import com.awalsatiajie.tugasmovie4.adapter.MovieListAdapter;
import com.awalsatiajie.tugasmovie4.model.DiscoverMovie;
import com.awalsatiajie.tugasmovie4.viewmodels.MovieFavoriteViewModel;
import com.awalsatiajie.tugasmovie4.support.SwipeToDeleteCallBack;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFavoriteFragment extends Fragment {
    private ProgressBar progressBar;
    private MovieListAdapter movieListAdapter;
    private FrameLayout frameLayout;
    private RecyclerView rvFavMovie;


    public MovieFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_movie_favorite, container, false);
        rvFavMovie = view.findViewById(R.id.rv_movie_favorit);
        progressBar = view.findViewById(R.id.pb_movie_favorit);
        frameLayout = view.findViewById(R.id.frame_movie_favorite);

        progressBar.setVisibility(View.VISIBLE);

        movieListAdapter = new MovieListAdapter(getContext());
        rvFavMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        rvFavMovie.setAdapter(movieListAdapter);

        MovieFavoriteViewModel viewModel = ViewModelProviders.of(this).get(MovieFavoriteViewModel.class);
        viewModel.setMovieFavorite();
        viewModel.getMovieMutableLiveData().observe(this, getListData);

        enableSwipeToDeleteAndUndo();

        return view;
    }

    private Observer<ArrayList<DiscoverMovie>> getListData = new Observer<ArrayList<DiscoverMovie>>() {
        @Override
        public void onChanged(@Nullable ArrayList<DiscoverMovie> discoverMovies) {
            movieListAdapter.setDiscoverMovies(discoverMovies);
            progressBar.setVisibility(View.GONE);
        }
    };

    private void enableSwipeToDeleteAndUndo(){
        SwipeToDeleteCallBack swipeToDeleteCallback = new SwipeToDeleteCallBack(getActivity()){
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i){
                final int position = viewHolder.getAdapterPosition();
                final DiscoverMovie discoverMovie = movieListAdapter.getData(position);

                movieListAdapter.removeItem(position, discoverMovie);

                Snackbar snackbar = Snackbar.make(frameLayout,
                        getResources().getString(R.string.has_remove_movie_favorite), Snackbar.LENGTH_SHORT);

                snackbar.setAction(getString(R.string.undo), new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        movieListAdapter.restoreItem(discoverMovie, position);
                        rvFavMovie.scrollToPosition(position);
                    }
                });

                snackbar.show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchHelper.attachToRecyclerView(rvFavMovie);
    }

}
