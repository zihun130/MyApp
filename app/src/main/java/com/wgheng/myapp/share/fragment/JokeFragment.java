package com.wgheng.myapp.share.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseFragment;
import com.wgheng.myapp.common.Constant;
import com.wgheng.myapp.share.adapter.RdRecyclerAdapter;
import com.wgheng.myapp.share.bean.RdBean;

import java.util.List;

import butterknife.BindView;

/**
 * Created by wgheng on 2017/7/12.
 */

public class JokeFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private RdRecyclerAdapter adapter;

    @Override
    protected View initView() {

        return View.inflate(getActivity(), R.layout.fragment_recommend, null);
    }

    @Override
    protected String getUrl() {
        return Constant.BS_JOKE;
    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    protected void processData(String json) {
        super.processData(json);
        Log.d("bs", "processData: "+json);
        RdBean rdBean = JSON.parseObject(json, RdBean.class);
        List<RdBean.ListBean> listBeans = rdBean.getList();

        if (adapter != null) {
            adapter.refresh(listBeans);
            return;
        }

        adapter = new RdRecyclerAdapter(getActivity(),listBeans);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                getData(Constant.BS_JOKE);
                refreshlayout.finishRefresh(2000);
            }
        });
        refreshLayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadmore(2000);
            }
        });
    }

}
