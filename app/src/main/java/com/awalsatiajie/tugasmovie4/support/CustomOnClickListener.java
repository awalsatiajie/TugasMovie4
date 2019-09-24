package com.awalsatiajie.tugasmovie4.support;

import android.view.View;

public class CustomOnClickListener implements View.OnClickListener {
    private int position;
    private OnItemClickCallback onItemClickCallback;

    public CustomOnClickListener(int position, OnItemClickCallback onItemClickCallback){
        this.onItemClickCallback = onItemClickCallback;
        this.position = position;
    }

    @Override
    public void onClick(View v) {
        onItemClickCallback.onItemClicked(v, position);
    }

    public interface OnItemClickCallback{
        void onItemClicked(View v, int position);
    }
}
