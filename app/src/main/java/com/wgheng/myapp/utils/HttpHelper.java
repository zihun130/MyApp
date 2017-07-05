package com.wgheng.myapp.utils;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by wgheng on 2017/6/21.
 */

public class HttpHelper {

    private OkHttpClient okHttpClient;

    /**
     * 单例的静态内部类实现
     */
    private HttpHelper() {
        okHttpClient = new OkHttpClient();
    }

    public static HttpHelper getInstance() {
        return Holder.instance;
    }

    private static class Holder {
        private static final HttpHelper instance = new HttpHelper();
    }

    /**
     * 封装GET请求
     *
     * @param url
     * @param callBack
     */
    public void get(String url, final CallBack callBack) {
        Request request = new Request.Builder()
                .url(url)
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                callBack.onFailure(e);
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                ResponseBody body = response.body();
                if (body != null) {
                    callBack.onResponse(body.string());
                }
            }
        });
    }

    public interface CallBack {
        void onFailure(IOException e);

        void onResponse(String json) throws IOException;
    }
}
