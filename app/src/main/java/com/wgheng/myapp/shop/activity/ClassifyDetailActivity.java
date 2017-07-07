package com.wgheng.myapp.shop.activity;

import android.util.Log;

import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseActivity;

public class ClassifyDetailActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_classify_detail;
    }

    @Override
    protected String getUrl() {
        return getIntent().getStringExtra("url");
    }

    @Override
    protected void processData(String json) {
        Log.d("ClassifyDetailActivity", "processData: " + json);
    }
}
