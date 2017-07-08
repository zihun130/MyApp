package com.wgheng.myapp.dazen.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseFragment;
import com.wgheng.myapp.common.Constant;
import com.wgheng.myapp.dazen.activity.DarenDetailActivity;
import com.wgheng.myapp.dazen.adapter.DarenRecyclerAdapter;
import com.wgheng.myapp.dazen.bean.DazenBean;
import com.wgheng.myapp.shop.adapter.ClassifyRecyclerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
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
    @BindView(R.id.rg_menu_options)
    RadioGroup rgMenuOptions;
    Unbinder unbinder1;
    private DarenRecyclerAdapter adapter;
    private List<DazenBean.DataBean.ItemsBean> itemsBeans;
    private DazenBean dazenBean;
    private boolean isLoadMore = false;
    private boolean isRGShow = false;
    private int page = 1;
    private String orderBy = "";

    @Override
    protected View initView() {
        View rooView = View.inflate(getActivity(), R.layout.fragment_dazen, null);
        rooView.setBackgroundColor(Color.parseColor("#808080"));
        return rooView;
    }

    @Override
    protected void initData() {
        page = 1;
        orderBy = "";
        ivSearch.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.VISIBLE);
        ivMenu.setVisibility(View.VISIBLE);
        rgMenuOptions.setOnCheckedChangeListener(new OnCheckedChangeListener());
        tvTitle.setText("达人");
        rgMenuOptions.check(R.id.rb_default_recommend);
    }

    @Override
    protected String getUrl() {
        return combineUrl();
    }

    private String combineUrl() {
        return Constant.DAREN_URL_PART1 + orderBy + Constant.DAREN_URL_PART2 + page + Constant.DAREN_URL_PART3;
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

        adapter.setOnItemClickListener(new ClassifyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(getActivity(), DarenDetailActivity.class);
                intent.putExtra("user_id", itemsBeans.get(position - 1).getUid());
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.iv_search, R.id.iv_menu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                Toast.makeText(getActivity(), "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_menu:
                if (!isRGShow) {
                    rgMenuOptions.setVisibility(View.VISIBLE);
                    ivMenu.setImageResource(R.drawable.ic_pack_close);
                } else {
                    rgMenuOptions.setVisibility(View.GONE);
                    ivMenu.setImageResource(R.drawable.actionbar_navigation_menu);
                }
                isRGShow = !isRGShow;
                break;
        }
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

    private class OnCheckedChangeListener implements RadioGroup.OnCheckedChangeListener {
        @Override
        public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
            isLoadMore = false;
            page = 1;
            switch (checkedId) {
                case R.id.rb_default_recommend:
                    orderBy = "";
                    break;
                case R.id.rb_most_recommend:
                    orderBy = "&orderby=goods_sum";
                    break;
                case R.id.rb_most_popular:
                    orderBy = "&orderby=followers";
                    break;
                case R.id.rb_latest_recommend:
                    orderBy = "&orderby=reg_time";
                    break;
                case R.id.rb_latest_join:
                    orderBy = "&orderby=action_time";
                    break;
            }
            getData(combineUrl());
            rgMenuOptions.setVisibility(View.GONE);
            ivMenu.setImageResource(R.drawable.actionbar_navigation_menu);
            isRGShow = false;
        }
    }
}
