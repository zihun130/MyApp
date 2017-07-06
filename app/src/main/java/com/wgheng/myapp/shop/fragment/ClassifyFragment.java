package com.wgheng.myapp.shop.fragment;

import android.graphics.Color;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseFragment;
import com.wgheng.myapp.common.Constant;
import com.wgheng.myapp.shop.adapter.ClassifyRecyclerAdapter;
import com.wgheng.myapp.shop.bean.ClassifyBean;

import java.util.List;

import butterknife.BindView;

/**
 * Created by wgheng on 2017/7/5.
 */

public class ClassifyFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    private ClassifyRecyclerAdapter adapter;

    @Override
    protected View initView() {
        View rootView = View.inflate(getActivity(), R.layout.fragment_shop_child, null);
        rootView.setBackgroundColor(Color.parseColor("#1e2125"));
        return rootView;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected String getUrl() {
        return Constant.CLASSIFY_URL;
    }

    @Override
    protected void processData(String s) {
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
}
