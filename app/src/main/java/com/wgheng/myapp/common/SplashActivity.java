package com.wgheng.myapp.common;

import android.content.Intent;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseActivity;

import butterknife.BindView;

public class SplashActivity extends BaseActivity {


    @BindView(R.id.iv_loading_start)
    ImageView ivLoadingStart;
    private Handler handler = new Handler();

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {
        Glide.with(this).load(R.drawable.loading_start).into(ivLoadingStart);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }, 3000);

    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacksAndMessages(null);
    }
}
