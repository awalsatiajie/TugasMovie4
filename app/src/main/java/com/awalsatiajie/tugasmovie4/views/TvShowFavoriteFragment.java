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
import com.awalsatiajie.tugasmovie4.adapter.TvShowListAdapter;
import com.awalsatiajie.tugasmovie4.model.DiscoverTvShow;
import com.awalsatiajie.tugasmovie4.support.SwipeToDeleteCallBack;
import com.awalsatiajie.tugasmovie4.viewmodels.TvShowFavoriteViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFavoriteFragment extends Fragment {
    private ProgressBar progressBar;
    private TvShowListAdapter tvShowListAdapter;
    private RecyclerView recyclerView;
    private FrameLayout frameLayout;


    public TvShowFavoriteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tv_show_favorite, container, false);
        progressBar = view.findViewById(R.id.pb_tv_favorit);
        recyclerView = view.findViewById(R.id.rv_tv_favorit);
        frameLayout = view.findViewById(R.id.frame_tv_favorite);

        setProgressBar(true);

        tvShowListAdapter = new TvShowListAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(tvShowListAdapter);

        TvShowFavoriteViewModel tvShowFavoriteViewModel = ViewModelProviders.of(this).get(TvShowFavoriteViewModel.class);
        tvShowFavoriteViewModel.setTvShowFavorite();
        tvShowFavoriteViewModel.getArrayListMutableLiveData().observe(this, getListTvShowFav);

        enableSwipeToDeleteAndUndo();

        return view;
    }

    private Observer<ArrayList<DiscoverTvShow>> getListTvShowFav = new Observer<ArrayList<DiscoverTvShow>>() {
        @Override
        public void onChanged(@Nullable ArrayList<DiscoverTvShow> discoverTvShows) {
            tvShowListAdapter.setdIscoverTvShowArrayList(discoverTvShows);
            setProgressBar(false);
        }
    };

    private void setProgressBar(boolean b){
        if(b){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
        }
    }

    private void enableSwipeToDeleteAndUndo(){
        SwipeToDeleteCallBack swipeToDeleteCallback = new SwipeToDeleteCallBack(getActivity()){
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i){
                final int position = viewHolder.getAdapterPosition();
                final DiscoverTvShow discoverTvShow= tvShowListAdapter.getItem(position);

                tvShowListAdapter.removeItem(position, discoverTvShow);

                Snackbar snackbar = Snackbar.make(frameLayout,
                        getResources().getString(R.string.has_remove_tvshow_favorite), Snackbar.LENGTH_SHORT);

                snackbar.setAction(getString(R.string.undo), new View.OnClickListener(){

                    @Override
                    public void onClick(View v) {
                        tvShowListAdapter.restoreItem(position, discoverTvShow);
                        recyclerView.scrollToPosition(position);
                    }
                });

                snackbar.show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeToDeleteCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

}
