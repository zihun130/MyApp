package com.wgheng.myapp.common;

import android.app.Application;
import android.content.Context;
import android.util.Log;

/**
 * Created by wgheng on 2017/7/7.
 */

public class MyApplication extends Application {

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("dbhelper", "onCreate: ");
        context = getApplicationContext();
        CartDataHelper.getInstance().initDB(this);
    }

    public static Context getContext() {
        return context;
    }
}
