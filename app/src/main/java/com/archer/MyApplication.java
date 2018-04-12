package com.archer;

import android.app.Application;

import com.archer.util.CrashUtils;

/**
 * Created by Long on 2017/4/28.
 */
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        CrashUtils.getInstance().init(this);
    }
}
