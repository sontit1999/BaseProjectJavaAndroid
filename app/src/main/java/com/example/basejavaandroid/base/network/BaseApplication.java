package com.example.basejavaandroid.base.network;

import android.app.Application;
import android.content.Context;

public class BaseApplication extends Application {
    private static BaseApplication mApp;

    @Override
    public void onCreate() {
        super.onCreate();
        mApp = this;
    }
    public static Context getContext() {
        return mApp;
    }
}
