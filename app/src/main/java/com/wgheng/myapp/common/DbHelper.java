package com.wgheng.myapp.common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by wgheng on 2017/7/12.
 */

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(Context context) {
        super(context, "liangcang.db", null, 1);
        Log.d("dbhelper", "DbHelper: ");

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("dbhelper", "onCreate: "+TableCart.CREATE_TABLE);
        db.execSQL(TableCart.CREATE_TABLE);
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
