package com.wgheng.myapp.activity;

import android.content.Intent;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseActivity;

import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

public class SplashActivity extends BaseActivity {


    @BindView(R.id.iv_loading_start)
    ImageView ivLoadingStart;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }
    @Override
    protected void initData() {
        Glide.with(this).load(R.drawable.loading_start).into(ivLoadingStart);

        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this,MainActivity.class));
                finish();
            }
        }, 3000);

    }

}
