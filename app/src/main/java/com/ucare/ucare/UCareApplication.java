package com.ucare.ucare;

/**
 * Created by Wien on 1/14/2016.
 */

import android.app.Application;
import android.content.Context;

import com.ucare.ucare.network.ServiceGenerator;
import com.ucare.ucare.network.UCareAPIService;
import com.ucare.ucare.utils.TinyDB;

public class UCareApplication extends Application {

    private static UCareApplication mInstance;
    public static UCareAPIService mNetworkService;

    public static TinyDB TDB;

    @Override
    public void onCreate() {
        super.onCreate();

        TDB = new TinyDB(getApplicationContext());

        mNetworkService = ServiceGenerator.createService(UCareAPIService.class, getApplicationContext());
        mInstance = this;
    }

    public static synchronized UCareApplication getInstance() {
        return mInstance;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }
}