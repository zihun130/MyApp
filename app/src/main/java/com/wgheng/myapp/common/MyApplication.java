package com.wgheng.myapp.common;

import android.app.Application;

import com.lzy.okgo.OkGo;

/**
 * Created by wgheng on 2017/7/7.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.getInstance().init(this);
    }
}
