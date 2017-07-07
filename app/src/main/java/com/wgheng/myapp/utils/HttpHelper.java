package com.wgheng.myapp.utils;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import okhttp3.OkHttpClient;

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
//        Request request = new Request.Builder()
//                .url(url)
//                .build();
//
//        okHttpClient.newCall(request).enqueue(new Callback() {
//            @Override
//            public void onFailure(@NonNull Call call, @NonNull IOException e) {
//                callBack.onFailure(e);
//            }
//
//            @Override
//            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
//                ResponseBody body = response.body();
//                if (body != null) {
//                    callBack.onResponse(body.string());
//                }
//            }
//        });

        OkGo.<String>get(url).execute(new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                callBack.onResponse(response.body().toString());
            }

            @Override
            public void onError(Response<String> response) {
                super.onError(response);
                callBack.onFailure(response.body().toString());
            }
        });
    }

    public interface CallBack {
        void onFailure(String message);

        void onResponse(String json);
    }
}
