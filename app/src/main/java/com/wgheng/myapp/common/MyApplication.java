package com.wgheng.myapp.common;

import android.app.Application;
import android.content.Context;

/**
 * Created by wgheng on 2017/7/7.
 */

public class MyApplication extends Application {

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        CartDataHelper.getInstance().init();
    }

    public static Context getContext() {
        return context;
    }
}
