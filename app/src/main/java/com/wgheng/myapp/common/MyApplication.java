package com.wgheng.myapp.common;

import android.content.Context;
import android.util.Log;

import com.mob.MobApplication;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by wgheng on 2017/7/7.
 */

public class MyApplication extends MobApplication {

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("dbhelper", "onCreate: ");
        context = getApplicationContext();
        CartDataHelper.getInstance().initDB(this);
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
    }

    public static Context getContext() {
        return context;
    }
}
