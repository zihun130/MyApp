package com.wgheng.myapp.shop.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseFragment;
import com.wgheng.myapp.common.Constant;
import com.wgheng.myapp.shop.adapter.ClassifyRecyclerAdapter;
import com.wgheng.myapp.shop.bean.ClassifyBean;
import com.wgheng.myapp.utils.ConnectUtils;

import java.util.List;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by wgheng on 2017/7/5.
 */

public class ClassifyFragment extends BaseFragment {


    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    PtrClassicFrameLayout refresh;
    private ClassifyRecyclerAdapter adapter;

    @Override
    protected View initView() {
        return View.inflate(getActivity(), R.layout.fragment_shop_child, null);
    }

    @Override
    protected void initData() {
        super.initData();
        refresh.setPtrHandler(new RefreshListener());
        getData();
    }

    private void getData() {
        ConnectUtils.getDataFromNet(Constant.CLASSIFY_URL)
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

    private void processData(String s) {
        Log.d("tag", "processData: "+s);
        ClassifyBean classifyBean = JSON.parseObject(s, ClassifyBean.class);
        List<ClassifyBean.DataBean.ItemsBean> itemsBeans = classifyBean.getData().getItems();

        initRecyclerView(itemsBeans);
    }

    private void initRecyclerView(List<ClassifyBean.DataBean.ItemsBean> itemsBeans) {
        adapter = new ClassifyRecyclerAdapter(getActivity(),itemsBeans);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
    }

    private class RefreshListener implements PtrHandler {
        @Override
        public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
            return false;
        }

        @Override
        public void onRefreshBegin(PtrFrameLayout frame) {

        }
    }
}
