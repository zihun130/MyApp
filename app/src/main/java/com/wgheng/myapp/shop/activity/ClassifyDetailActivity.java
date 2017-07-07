package com.wgheng.myapp.shop.activity;

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
import com.wgheng.myapp.base.BaseActivity;
import com.wgheng.myapp.shop.adapter.ClassifyDetailBean;
import com.wgheng.myapp.shop.adapter.ClassifyDetailRecyclerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ClassifyDetailActivity extends BaseActivity {


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
    public int getLayoutId() {
        return R.layout.activity_classify_detail;
    }

    @Override
    protected void initView() {
        super.initView();
        ivBack.setVisibility(View.VISIBLE);
        ivCart.setVisibility(View.VISIBLE);
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("商店");
    }

    @Override
    protected String getUrl() {
        return getIntent().getStringExtra("url");
    }

    @Override
    protected void processData(String json) {
        Log.d("tag", "processData: " + json);
        ClassifyDetailBean classifyBean = JSON.parseObject(json, ClassifyDetailBean.class);
        itemsBeans = classifyBean.getData().getItems();

        initRecyclerView(itemsBeans);
    }

    private void initRecyclerView(List<ClassifyDetailBean.DataBean.ItemsBean> itemsBeans) {
        adapter = new ClassifyDetailRecyclerAdapter(this, itemsBeans);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter.setOnItemClickListener(new ClassifyDetailRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(ClassifyDetailActivity.this, position+"", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick({R.id.iv_back, R.id.ll_price_filter})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_price_filter:
                Toast.makeText(ClassifyDetailActivity.this, "筛选", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
