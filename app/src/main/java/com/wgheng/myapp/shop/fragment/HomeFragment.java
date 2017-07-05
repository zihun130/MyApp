package com.wgheng.myapp.shop.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseFragment;
import com.wgheng.myapp.common.Constant;
import com.wgheng.myapp.shop.adapter.HomeRecyclerAdapter;
import com.wgheng.myapp.shop.bean.HomeBean;
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

public class HomeFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    PtrClassicFrameLayout refresh;
    private HomeRecyclerAdapter adapter;

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
        ConnectUtils.getDataFromNet(Constant.HOME_URL)
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
        HomeBean homeBean = JSON.parseObject(s,HomeBean.class);
        List<HomeBean.DataBean.ItemsBean.ListBean> listBeen = homeBean.getData().getItems().getList();
        initRecyclerView(listBeen);

    }

    private void initRecyclerView(List<HomeBean.DataBean.ItemsBean.ListBean> listBeen) {
        adapter = new HomeRecyclerAdapter(getActivity(),listBeen);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
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
