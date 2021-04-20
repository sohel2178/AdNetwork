package com.forbitbd.myadnetwork.api;

import com.forbitbd.myadnetwork.models.AdRequest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiClient {

    @POST("/api/api/adnetwork")
    Call<ResponseBody> loadAd(@Body AdRequest adRequest);


}
