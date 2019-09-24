package com.awalsatiajie.tugasmovie4;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView txtNomorku = findViewById(R.id.mynumber);
        txtNomorku.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.mynumber:
                telpon();
                break;
        }
    }

    private void telpon(){
        String nomor = "088802204095";
        Intent dialPhoneIntent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:"+nomor));
        startActivity(dialPhoneIntent);
    }
}
