package com.forbitbd.myadnetwork.ui;

import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.forbitbd.myadnetwork.models.AdRequest;
import com.forbitbd.myadnetwork.api.ApiClient;
import com.forbitbd.myadnetwork.api.ServiceGenerator;
import com.forbitbd.myadnetwork.ui.fullScreenAd.CloseListener;
import com.forbitbd.myadnetwork.ui.fullScreenAd.FullScreenAd;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InterstitialAd implements CloseListener {

    private AppCompatActivity activity;
    private boolean isLoaded;
    private byte[] imageArr;

    private FullScreenAdListener listener;


    public InterstitialAd(AppCompatActivity activity) {
        this.activity = activity;
    }

    public void setAdListener(FullScreenAdListener listener){
        this.listener = listener;
    }


    public void loadInterstitialAd(String appId,String adUnitId){

        AdRequest adRequest = new AdRequest(appId,adUnitId);

        ApiClient client = ServiceGenerator.createService(ApiClient.class);

        client.loadAd(adRequest)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            Log.d("HHHHH","OK");
                            InputStream inputStream = response.body().byteStream();
                            ByteArrayOutputStream bos = new ByteArrayOutputStream();
                            int next = 0;
                            try {
                                next = inputStream.read();
                                while (next > -1) {
                                    bos.write(next);
                                    next = inputStream.read();
                                }
                                bos.flush();
                                imageArr = bos.toByteArray();
                                bos.close();

                                if(listener!=null){
                                    listener.onLoaded();
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                                if(listener!=null){
                                    listener.onFailed("IO Exception");
                                }
                            }


                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Log.d("HHHHH","ERROR"+t.getMessage());

                        if(listener!=null){
                            listener.onFailed("Server Error");
                        }
                    }
                });

    }

    public boolean isLoaded(){
        if(imageArr!=null){
            return true;
        }else {
            return false;
        }
    }

    public void showAd(){
        FullScreenAd fullScreenAd = FullScreenAd.newInstance(imageArr);
        fullScreenAd.setCancelable(false);
        fullScreenAd.setCloseListener(this);
        fullScreenAd.show(activity.getSupportFragmentManager(),"HHHHH");
    }


    @Override
    public void onCloseAd() {
        imageArr=null;
        if(listener!=null){
            listener.onClosed();
        }
    }

    @Override
    public void onShowAd() {
        // SEnd Data To Server
    }
}
