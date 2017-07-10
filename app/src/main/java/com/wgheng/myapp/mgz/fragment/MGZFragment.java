package com.wgheng.myapp.mgz.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseFragment;
import com.wgheng.myapp.common.Constant;
import com.wgheng.myapp.mgz.activity.MGZDetailActivity;
import com.wgheng.myapp.mgz.adapter.MGZRecyclerAdapter;
import com.wgheng.myapp.mgz.bean.MGZBean;
import com.wgheng.myapp.shop.activity.WebActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wgheng on 2017/7/5.
 */

public class MGZFragment extends BaseFragment {
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    @BindView(R.id.ts_month)
    TextSwitcher tsMonth;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private MGZRecyclerAdapter adapter;
    private List<MGZBean> mgzBeans;
    private boolean isLoadMore = false;
    private int page = 1;
    private int currentPosition = 0;

    @Override
    protected View initView() {
        View rooView = View.inflate(getActivity(), R.layout.fragment_mgz, null);
        rooView.setBackgroundColor(Color.parseColor("#808080"));
        return rooView;
    }

    @Override
    protected void initData() {
        page = 1;
        super.initData();
        tvTitle.setText("杂志");
        tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), MGZDetailActivity.class));
                getActivity().overridePendingTransition(R.anim.activity_in_top, R.anim.activity_out_alpha);
            }
        });

        tsMonth.setFactory(new ViewSwitcher.ViewFactory() {

            @Override
            public View makeView() {
                TextView tv = new TextView(getActivity());
                tv.setTextSize(15);
                tv.setTextColor(Color.parseColor("#5e7fa3"));
                return tv;
            }
        });
        tsMonth.setText("Today");

    }

    @Override
    protected String getUrl() {
        return Constant.MGZ_URL_PART1 + page + Constant.MGZ_URL_PART2;
    }

    @Override
    protected void processData(String json) {
        Log.d("mgz", "processData: " + json);

        try {
            JSONObject obj = new JSONObject(json);
            JSONObject data = obj.optJSONObject("data");
            JSONObject items = data.optJSONObject("items");
            JSONArray keys = items.optJSONArray("keys");
            JSONObject infos = items.optJSONObject("infos");

            mgzBeans = new ArrayList<>();

            for (int i = 0; i < keys.length(); i++) {
                JSONArray monthInfos = infos.optJSONArray(keys.get(i) + "");
                for (int j = 0; j < monthInfos.length(); j++) {
                    MGZBean mgzBean = new MGZBean();
                    JSONObject objMGZ = monthInfos.optJSONObject(j);
                    mgzBean.setTopic_name(objMGZ.optString("topic_name"));
                    mgzBean.setTopic_url(objMGZ.optString("topic_url"));
                    mgzBean.setCat_name(objMGZ.optString("cat_name"));
                    mgzBean.setCover_img(objMGZ.optString("cover_img"));
                    mgzBean.setMonthInfo(keys.get(i) + "");
                    mgzBeans.add(mgzBean);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        Log.d("TAG", "processData: " + mgzBeans.toString());

        if (!isLoadMore) {
            initRecyclerView();
            recyclerView.refreshComplete();
        } else {
            this.mgzBeans.addAll(mgzBeans);
            adapter.notifyDataSetChanged();
            recyclerView.loadMoreComplete();
        }
        isLoadMore = false;//加载完成后重置为false，否则ViewPager切换释放Fragment后后导致adapter为空
    }

    private void initRecyclerView() {
        adapter = new MGZRecyclerAdapter(getActivity(), mgzBeans);
        recyclerView.setAdapter(adapter);
        final LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        recyclerView.setLoadingListener(new LoadingListener());

        //设置刷新样式
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
        adapter.setOnItemClickListener(new MGZRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(getActivity(), WebActivity.class);
                intent.putExtra("title", mgzBeans.get(position - 1).getTopic_name());
                intent.putExtra("url", mgzBeans.get(position - 1).getTopic_url());
                startActivity(intent);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int position = manager.findFirstVisibleItemPosition();

                if (currentPosition != position && position <= mgzBeans.size() - 1) {
                    String text = mgzBeans.get(position - 1).getMonthInfo();
                    tsMonth.setText(text.substring(5));
                    currentPosition = position;
                }

            }
        });


    }

    class LoadingListener implements XRecyclerView.LoadingListener {

        @Override
        public void onRefresh() {
            isLoadMore = false;
            page = 1;
            getData(Constant.MGZ_URL_PART1 + page + Constant.MGZ_URL_PART2);
        }

        @Override
        public void onLoadMore() {
            isLoadMore = true;
            page++;
            getData(Constant.MGZ_URL_PART1 + page + Constant.MGZ_URL_PART2);

        }
    }
}
