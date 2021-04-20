package com.forbitbd.myadnetwork.models;

import java.io.Serializable;

public class AdRequest implements Serializable {

    private String appId;
    private String adUnitId;

    public AdRequest() {
    }


    public AdRequest(String appId, String adUnitId) {
        this.appId = appId;
        this.adUnitId = adUnitId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAdUnitId() {
        return adUnitId;
    }

    public void setAdUnitId(String adUnitId) {
        this.adUnitId = adUnitId;
    }
}
