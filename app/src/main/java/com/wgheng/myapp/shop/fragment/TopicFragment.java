package com.wgheng.myapp.shop.fragment;

import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseFragment;
import com.wgheng.myapp.common.Constant;
import com.wgheng.myapp.shop.adapter.TopicRecyclerAdapter;
import com.wgheng.myapp.shop.bean.TopicBean;

import java.util.List;

import butterknife.BindView;

/**
 * Created by wgheng on 2017/7/5.
 */

public class TopicFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    private TopicRecyclerAdapter adapter;
    private List<TopicBean.DataBean.ItemsBean> itemsBeans;
    private TopicBean topicBean;
    private boolean isLoadMore = false;
    private int page = 1;

    @Override
    protected View initView() {
        View rooView = View.inflate(getActivity(), R.layout.fragment_shop_child, null);
        rooView.setBackgroundColor(Color.parseColor("#808080"));
        return rooView;
    }

    @Override
    protected void initData() {
        page = 1;
        super.initData();
    }

    @Override
    protected String getUrl() {
        return Constant.TOPIC_URL_PART1 + page + Constant.TOPIC_URL_PART2;
    }

    @Override
    protected void processData(String json) {
        Log.d("brand", "processData: " + json);
        topicBean = JSON.parseObject(json, TopicBean.class);
        List<TopicBean.DataBean.ItemsBean> itemsBeans = topicBean.getData().getItems();

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
        adapter = new TopicRecyclerAdapter(getActivity(), itemsBeans);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLoadingListener(new LoadingListener());

        //设置刷新样式
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
    }

    class LoadingListener implements XRecyclerView.LoadingListener {
        @Override
        public void onRefresh() {
            isLoadMore = false;
            page = 1;
            getData(Constant.TOPIC_URL_PART1 + page + Constant.TOPIC_URL_PART2);
        }

        @Override
        public void onLoadMore() {
            isLoadMore = true;
            if (topicBean.getData().isHas_more()) {
                page++;
                getData(Constant.TOPIC_URL_PART1 + page + Constant.TOPIC_URL_PART2);
            } else {
                Toast.makeText(getActivity(), "没有更多了", Toast.LENGTH_SHORT).show();
                recyclerView.loadMoreComplete();
            }
        }
    }
}
