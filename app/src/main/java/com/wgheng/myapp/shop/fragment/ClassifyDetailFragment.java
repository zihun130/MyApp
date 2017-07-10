package com.wgheng.myapp.shop.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseFragment;
import com.wgheng.myapp.common.MainActivity;
import com.wgheng.myapp.shop.activity.GoodsActivity;
import com.wgheng.myapp.shop.adapter.ClassifyDetailRecyclerAdapter;
import com.wgheng.myapp.shop.bean.ClassifyDetailBean;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ClassifyDetailFragment extends BaseFragment {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_cart)
    ImageView ivCart;
    @BindView(R.id.ll_price_filter)
    LinearLayout llPriceFilter;
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    private ClassifyDetailRecyclerAdapter adapter;
    private List<ClassifyDetailBean.DataBean.ItemsBean> itemsBeans;


    @Override
    protected View initView() {
        return View.inflate(getActivity(), R.layout.activity_classify_detail, null);
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("商店");
        ivBack.setVisibility(View.VISIBLE);
        ivCart.setVisibility(View.VISIBLE);
    }

    @Override
    protected String getUrl() {
        Bundle bundle = getArguments();
        return bundle.getString("url");
    }

    @Override
    protected void processData(String json) {
        Log.d("tag", "processData: " + json);
        ClassifyDetailBean classifyBean = JSON.parseObject(json, ClassifyDetailBean.class);
        itemsBeans = classifyBean.getData().getItems();

        initRecyclerView(itemsBeans);
    }

    private void initRecyclerView(final List<ClassifyDetailBean.DataBean.ItemsBean> itemsBeans) {
        adapter = new ClassifyDetailRecyclerAdapter(getActivity(), itemsBeans);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setLoadingMoreEnabled(false);
        recyclerView.setPullRefreshEnabled(false);
        adapter.setOnItemClickListener(new ClassifyDetailRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(getActivity(), GoodsActivity.class);
                intent.putExtra("goods_id", itemsBeans.get(position - 1).getGoods_id());
                startActivity(intent);
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.ll_price_filter})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                getActivity().getSupportFragmentManager().popBackStack();
                ((MainActivity) getActivity()).fragments.remove(5);
                break;
            case R.id.ll_price_filter:
                Toast.makeText(getActivity(), "筛选", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
