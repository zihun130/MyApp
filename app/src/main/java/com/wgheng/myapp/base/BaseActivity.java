package com.wgheng.myapp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.wgheng.myapp.utils.ConnectUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by wgheng on 2017/7/5.
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Unbinder bind;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        bind = ButterKnife.bind(this);

        initView();

        initData();
    }

    public abstract int getLayoutId();

    protected  void initView() {

    }


    protected void initData() {
        getData(getUrl());
    }

    /**
     * 联网请求数据需重写此方法
     * @return url地址
     */
    protected String getUrl() {
        return null;
    }

    /**
     * 联网请求数据
     * @param url
     */
    protected void getData(String url) {

        if(TextUtils.isEmpty(url)) {
            return;
        }

        ConnectUtils.getDataFromNet(url)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        processData(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
    }

    /**
     * 联网请求数据需重写此方法处理数据
     * @param json 联网获取的json
     */
    protected void processData(String json) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();
    }
}
