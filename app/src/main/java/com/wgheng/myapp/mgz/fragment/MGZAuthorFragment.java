package com.wgheng.myapp.mgz.fragment;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;

import com.alibaba.fastjson.JSON;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseFragment;
import com.wgheng.myapp.common.Constant;
import com.wgheng.myapp.mgz.activity.MGZAuthorDetailActivity;
import com.wgheng.myapp.mgz.adapter.AuthorRecyclerAdapter;
import com.wgheng.myapp.mgz.bean.AuthorBean;
import com.wgheng.myapp.shop.adapter.ClassifyRecyclerAdapter;

import java.util.List;

import butterknife.BindView;

/**
 * Created by wgheng on 2017/7/10.
 */

public class MGZAuthorFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    private AuthorRecyclerAdapter adapter;
    private List<AuthorBean.DataBean.ItemsBean> itemsBeans;

    @Override
    protected View initView() {
        return View.inflate(getActivity(), R.layout.fragment_recycler_list, null);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected String getUrl() {
        return Constant.AUTHOR_URL;
    }

    @Override
    protected void processData(String s) {
        Log.d("tag", "processData: " + s);
        AuthorBean classifyBean = JSON.parseObject(s, AuthorBean.class);
        itemsBeans = classifyBean.getData().getItems();

        initRecyclerView(itemsBeans);
    }

    private void initRecyclerView(final List<AuthorBean.DataBean.ItemsBean> itemsBeans) {
        adapter = new AuthorRecyclerAdapter(getActivity(), itemsBeans);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setLoadingMoreEnabled(false);
        recyclerView.setPullRefreshEnabled(false);

        adapter.setOnItemClickListener(new ClassifyRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                String author_id = itemsBeans.get(position - 1).getAuthor_id();
                Intent intent = new Intent(getActivity(), MGZAuthorDetailActivity.class);
                intent.putExtra("author_id", author_id);
                intent.putExtra("title", itemsBeans.get(position - 1).getAuthor_name());
                startActivity(intent);
                getActivity().finish();
                getActivity().overridePendingTransition(R.anim.activity_in_alpha_0_1,R.anim.activity_out_top);
            }
        });
    }
}
