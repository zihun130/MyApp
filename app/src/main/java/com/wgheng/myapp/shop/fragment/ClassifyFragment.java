package com.wgheng.myapp.shop.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
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
    private List<ClassifyBean.DataBean.ItemsBean> itemsBeans;
    private String[] urls = {Constant.HOUSEHOLDER_URL, Constant.FURNITURE_URL, Constant.STATIONERY_URL, Constant.DIGIT_URL, Constant.PALY_URL,
            Constant.KITCHEN_URL, Constant.FOODS_URL, Constant.MENSWEAR_URL, Constant.WOMENSWEAR_URL, Constant.CHILDSWEAR_URL,
            Constant.SHOES_URL, Constant.DECOR_URL, Constant.MEICARE_URL, Constant.OUTSIDE_URL, Constant.PLANT_URL,
            Constant.BOOK_URL, Constant.GIFTCLASSIFY_URL, Constant.RECOMMEND_URL, Constant.ARTS_URL};

    @Override
    protected View initView() {
        View rootView = View.inflate(getActivity(), R.layout.fragment_recycler_list, null);
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
        Log.d("tag", "processData: " + s);
        ClassifyBean classifyBean = JSON.parseObject(s, ClassifyBean.class);
        itemsBeans = classifyBean.getData().getItems();

        initRecyclerView(itemsBeans);
    }

    private void initRecyclerView(List<ClassifyBean.DataBean.ItemsBean> itemsBeans) {
        adapter = new ClassifyRecyclerAdapter(getActivity(), itemsBeans);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setLoadingMoreEnabled(false);
        recyclerView.setPullRefreshEnabled(false);

        adapter.setOnItemClickListener(new ClassifyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                Bundle bundle = new Bundle();
                bundle.putString("url",urls[position - 1]);
                ClassifyDetailFragment classifyDetailFragment = new ClassifyDetailFragment();
                classifyDetailFragment.setArguments(bundle);
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.add(R.id.fl_main,classifyDetailFragment).addToBackStack(null).commit();
            }
        });
    }
}
