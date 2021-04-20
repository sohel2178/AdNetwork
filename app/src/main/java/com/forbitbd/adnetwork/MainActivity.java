package com.forbitbd.adnetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.forbitbd.myadnetwork.ui.FullScreenAdListener;
import com.forbitbd.myadnetwork.ui.InterstitialAd;

public class MainActivity extends AppCompatActivity {

    private InterstitialAd interstitialAd;
    private Button btnShowAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InterstitialAd interstitialAd = new InterstitialAd(this);
        interstitialAd.loadInterstitialAd("hjkdf","jhjhkjhk");

        interstitialAd.setAdListener(new FullScreenAdListener() {
            @Override
            public void onLoaded() {

            }

            @Override
            public void onFailed(String errorMessage) {

            }

            @Override
            public void onClosed() {
                Log.d("HHHHH","Ad is Closed");
                interstitialAd.loadInterstitialAd("hjkdf","jhjhkjhk");
            }
        });

        btnShowAd = findViewById(R.id.btn_show);
        btnShowAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(interstitialAd.isLoaded()){
                    interstitialAd.showAd();
                }
            }
        });
    }
}