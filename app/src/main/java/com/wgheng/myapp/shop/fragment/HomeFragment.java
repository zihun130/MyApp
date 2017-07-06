package com.wgheng.myapp.shop.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseFragment;
import com.wgheng.myapp.common.Constant;
import com.wgheng.myapp.shop.adapter.HomeRecyclerAdapter;
import com.wgheng.myapp.shop.bean.HomeBean;

import java.util.List;

import butterknife.BindView;

/**
 * Created by wgheng on 2017/7/5.
 */

public class HomeFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    private HomeRecyclerAdapter adapter;

    @Override
    protected View initView() {
        return View.inflate(getActivity(), R.layout.fragment_shop_child, null);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected String getUrl() {
        return Constant.HOME_URL;
    }

    @Override
    protected void processData(String s) {
        Log.d("tag", "processData: "+s);
        HomeBean homeBean = JSON.parseObject(s,HomeBean.class);
        List<HomeBean.DataBean.ItemsBean.ListBean> listBeans = homeBean.getData().getItems().getList();
        initRecyclerView(listBeans);
    }

    private void initRecyclerView(List<HomeBean.DataBean.ItemsBean.ListBean> listBeen) {
        adapter = new HomeRecyclerAdapter(getActivity(),listBeen);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
