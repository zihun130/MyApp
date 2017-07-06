package com.wgheng.myapp.dazen.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseFragment;
import com.wgheng.myapp.common.Constant;
import com.wgheng.myapp.dazen.adapter.DarenRecyclerAdapter;
import com.wgheng.myapp.dazen.bean.DazenBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by wgheng on 2017/7/5.
 */

public class DaRenFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_menu)
    ImageView ivMenu;
    Unbinder unbinder;
    private DarenRecyclerAdapter adapter;
    private List<DazenBean.DataBean.ItemsBean> itemsBeans;
    private DazenBean dazenBean;
    private boolean isLoadMore = false;
    private int page = 1;

    @Override
    protected View initView() {
        View rooView = View.inflate(getActivity(), R.layout.fragment_dazen, null);
        rooView.setBackgroundColor(Color.parseColor("#808080"));
        return rooView;
    }

    @Override
    protected void initData() {
        page = 1;
        super.initData();
        ivSearch.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.VISIBLE);
        ivMenu.setVisibility(View.VISIBLE);
        tvTitle.setText("达人");
    }

    @Override
    protected String getUrl() {
        return combineUrl();
    }

    private String combineUrl() {
        return Constant.DAREN_URL_PART1 + page + Constant.DAREN_URL_PART2;
    }

    @Override
    protected void processData(String json) {
        Log.d("brand", "processData: " + json);
        dazenBean = JSON.parseObject(json, DazenBean.class);
        List<DazenBean.DataBean.ItemsBean> itemsBeans = dazenBean.getData().getItems();

        if (!isLoadMore) {
            this.itemsBeans = itemsBeans;
            initRecyclerView();
            recyclerView.refreshComplete();
        } else {
            this.itemsBeans.addAll(itemsBeans);
            adapter.notifyDataSetChanged();
            recyclerView.loadMoreComplete();
        }
        isLoadMore = false;//加载完成后重置为false，否则ViewPager切换释放Fragment后后导致adapter为空
    }

    private void initRecyclerView() {
        adapter = new DarenRecyclerAdapter(getActivity(), itemsBeans);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setLoadingListener(new LoadingListener());

        //设置刷新样式
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    private class LoadingListener implements XRecyclerView.LoadingListener {
        @Override
        public void onRefresh() {
            isLoadMore = false;
            page = 1;
            getData(combineUrl());
        }

        @Override
        public void onLoadMore() {
            isLoadMore = true;
            if (dazenBean.getData().isHas_more()) {
                page++;
                getData(combineUrl());
            } else {
                Toast.makeText(getActivity(), "没有更多了", Toast.LENGTH_SHORT).show();
                recyclerView.loadMoreComplete();
            }
        }

    }
}
