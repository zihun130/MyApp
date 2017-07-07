package com.wgheng.myapp.utils;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by wgheng on 2017/7/6.
 */

public class ConnectUtils {

    /**
     * 联网获取数据
     * @param url
     * @return
     */
    public static Observable<String> getDataFromNet(final String url) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull final ObservableEmitter<String> e) throws Exception {
//                HttpHelper.getInstance().get(url, new HttpHelper.CallBack() {
//                    @Override
//                    public void onFailure(IOException ex) {
//                        e.onError(ex);
//                    }
//
//                    @Override
//                    public void onResponse(String json) throws IOException {
//                        e.onNext(json);
//                        e.onComplete();
//                    }
//                });

                HttpHelper.getInstance().get(url, new HttpHelper.CallBack() {
                    @Override
                    public void onFailure(String message) {
                        e.onError(new Exception(message));
                    }

                    @Override
                    public void onResponse(String json) {
                        e.onNext(json);
                        e.onComplete();
                    }
                });
            }
        }).subscribeOn(Schedulers.io());
    }
}
