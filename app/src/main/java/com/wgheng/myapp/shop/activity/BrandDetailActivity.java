package com.wgheng.myapp.shop.activity;

import android.support.annotation.IdRes;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseActivity;
import com.wgheng.myapp.common.Constant;
import com.wgheng.myapp.shop.adapter.BrandDetailRecyclerAdapter;
import com.wgheng.myapp.shop.bean.BrandDetailBean;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class BrandDetailActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.civ_brand_logo)
    CircleImageView civBrandLogo;
    @BindView(R.id.tv_brand_name)
    TextView tvBrandName;
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    @BindView(R.id.rg_brand)
    RadioGroup rgBrand;
    @BindView(R.id.tv_brand_story)
    TextView tvBrandStory;
    private BrandDetailRecyclerAdapter adapter;
    private List<BrandDetailBean.DataBean.ItemsBean> itemsBeans;

    @Override
    public int getLayoutId() {
        return R.layout.activity_brand_detail;
    }

    @Override
    protected void initView() {
        super.initView();
        ivBack.setVisibility(View.VISIBLE);
        rgBrand.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_brand_story:
                        recyclerView.setVisibility(View.GONE);
                        tvBrandStory.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rb_brand_product:
                        recyclerView.setVisibility(View.VISIBLE);
                        tvBrandStory.setVisibility(View.GONE);
                        break;
                }
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("品牌产品");
        tvBrandName.setText(getIntent().getStringExtra("brand_name"));
        Glide.with(this).load(getIntent().getStringExtra("image_url")).into(civBrandLogo);
        rgBrand.check(R.id.rb_brand_product);
    }

    @Override
    protected String getUrl() {
        String brand_id = getIntent().getStringExtra("brand_id");
        return Constant.BRAND_DETAIL_URL_PART1 + brand_id + Constant.BRAND_DETAIL_URL_PART2;
    }

    @Override
    protected void processData(String json) {
        Log.d("tag", "processData: " + json);
        BrandDetailBean classifyBean = JSON.parseObject(json, BrandDetailBean.class);
        itemsBeans = classifyBean.getData().getItems();
        tvBrandStory.setText(itemsBeans.get(0).getBrand_info().getBrand_desc());
        initRecyclerView(itemsBeans);
    }

    private void initRecyclerView(List<BrandDetailBean.DataBean.ItemsBean> itemsBeans) {
        adapter = new BrandDetailRecyclerAdapter(this, itemsBeans);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setPullRefreshEnabled(false);
        recyclerView.setLoadingMoreEnabled(false);
        adapter.setOnItemClickListener(new BrandDetailRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(BrandDetailActivity.this, position + "", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.iv_back)
    public void onClick() {
        finish();
    }

}
