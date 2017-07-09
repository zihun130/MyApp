package com.wgheng.myapp.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.wgheng.myapp.common.MyApplication;

/**
 * Created by wgheng on 2017/7/9.
 */

public class CacheUtils {

    public static void putString(String key,String value) {
        SharedPreferences sp = MyApplication.getContext().getSharedPreferences("liangcang", Context.MODE_PRIVATE);
        sp.edit().putString(key,value).commit();

    }

    public static String getString(String key) {
        SharedPreferences sp = MyApplication.getContext().getSharedPreferences("liangcang", Context.MODE_PRIVATE);
        return sp.getString(key, null);
    }
}
