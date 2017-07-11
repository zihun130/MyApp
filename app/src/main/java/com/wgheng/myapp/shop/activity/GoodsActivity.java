package com.wgheng.myapp.shop.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseActivity;
import com.wgheng.myapp.common.Constant;
import com.wgheng.myapp.shop.bean.BuyBean;
import com.wgheng.myapp.shop.bean.GoodsBean;
import com.wgheng.myapp.view.PullUpToLoadMore;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class GoodsActivity extends BaseActivity {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_brand_name1)
    TextView tvBrandName1;
    @BindView(R.id.tv_brand_name)
    TextView tvGoodName;
    @BindView(R.id.iv_like)
    ImageView ivLike;
    @BindView(R.id.tv_like_number)
    TextView tvLikeNumber;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.tv_advert)
    TextView tvAdvert;
    @BindView(R.id.tv_real_price)
    TextView tvRealPrice;
    @BindView(R.id.tv_origin_price)
    TextView tvOriginPrice;
    @BindView(R.id.fl_origin_price)
    FrameLayout flOriginPrice;
    @BindView(R.id.tv_pre_sold)
    TextView tvPreSold;
    @BindView(R.id.tv_choose_size)
    TextView tvChooseSize;
    @BindView(R.id.iv_brand_logo)
    ImageView ivBrandLogo;
    @BindView(R.id.ll_brand_detail)
    LinearLayout llBrandDetail;
    @BindView(R.id.rb_good_info)
    RadioButton rbGoodInfo;
    @BindView(R.id.rb_buy_tips)
    RadioButton rbBuyTips;
    @BindView(R.id.rg_brand)
    RadioGroup rgBrand;
    @BindView(R.id.tv_buy_tips)
    TextView tvBuyTips;
    @BindView(R.id.tv_after_sale_tips)
    TextView tvAfterSaleTips;
    @BindView(R.id.ll_buy_tips)
    LinearLayout llBuyTips;
    @BindView(R.id.ll_image_container)
    LinearLayout llImageContainer;
    @BindView(R.id.tv_good_name)
    TextView tvGoodDesc;
    @BindView(R.id.tv_brand_name3)
    TextView tvBrandName3;
    @BindView(R.id.tv_brand_desc)
    TextView tvBrandDesc;
    @BindView(R.id.ll_maybe_you_like)
    LinearLayout llMaybeYouLike;
    @BindView(R.id.ptlm)
    PullUpToLoadMore ptlm;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_cart)
    ImageView ivCart;
    @BindView(R.id.iv_call_center)
    ImageView ivCallCenter;
    @BindView(R.id.tv_add_cart)
    TextView tvAddCart;
    @BindView(R.id.tv_just_buy)
    TextView tvJustBuy;
    @BindView(R.id.ll_good_detail)
    LinearLayout llGoodDetail;
    @BindView(R.id.tv_brand_name2)
    TextView tvBrandName2;
    private GoodsBean.DataBean.ItemsBean itemsBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods;
    }

    @Override
    protected void initView() {
        rgBrand.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_good_info:
                        llBuyTips.setVisibility(View.GONE);
                        llGoodDetail.setVisibility(View.VISIBLE);
                        break;
                    case R.id.rb_buy_tips:
                        llBuyTips.setVisibility(View.VISIBLE);
                        llGoodDetail.setVisibility(View.GONE);
                        break;
                }
            }
        });
    }

    @Override
    protected String getUrl() {
        String goods_id = getIntent().getStringExtra("goods_id");
        return Constant.GOODS_DETIAL_URL_PART1 + goods_id + Constant.GOODS_DETIAL_URL_PART2;
    }

    @Override
    protected void initData() {
        super.initData();
        rgBrand.check(R.id.rb_good_info);
    }

    @Override
    protected void processData(String json) {
        Log.d("tag", "processData:goods " + json);
        GoodsBean goodsInfoBean = JSON.parseObject(json, GoodsBean.class);
        itemsBean = goodsInfoBean.getData().getItems();

        setPagerData(itemsBean);

        setDetails(itemsBean);

    }

    private void setDetails(GoodsBean.DataBean.ItemsBean itemsBean) {
        List<GoodsBean.DataBean.ItemsBean.GoodsInfoBean> goodsInfoBeans = itemsBean.getGoods_info();
        for (int i = 0; i < goodsInfoBeans.size(); i++) {
            if (goodsInfoBeans.get(i).getType() == 1) {

                ImageView imageView = new ImageView(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                imageView.setLayoutParams(params);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                Glide.with(this).load(goodsInfoBeans.get(i).getContent().getImg()).into(imageView);
                llImageContainer.addView(imageView);

            } else {

                TextView textView = new TextView(this);
                textView.setText(goodsInfoBeans.get(i).getContent().getText());
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.bottomMargin = 30;
                textView.setLayoutParams(params);

                if (goodsInfoBeans.get(i).getType() == 2) {
                    textView.setTextSize(15);
                    textView.setTextColor(Color.WHITE);
                } else {
                    textView.setTextSize(12);
                    textView.setTextColor(Color.parseColor("#8c8d8e"));
                }
                llImageContainer.addView(textView);
            }
        }
    }

    private void setPagerData(GoodsBean.DataBean.ItemsBean itemsBean) {
        banner.setImageLoader(new GlideImageLoader())
                .setImages(itemsBean.getImages_item())
                .start();
        if (!TextUtils.isEmpty(itemsBean.getDiscount_price())) {
            tvRealPrice.setText("￥" + itemsBean.getDiscount_price());
            tvOriginPrice.setText("￥" + itemsBean.getPrice());
        } else {
            tvRealPrice.setText("￥" + itemsBean.getPrice());
            flOriginPrice.setVisibility(View.GONE);
        }
        tvBrandName1.setText(itemsBean.getBrand_info().getBrand_name());
        tvBrandName2.setText(itemsBean.getBrand_info().getBrand_name());
        tvBrandName3.setText(itemsBean.getBrand_info().getBrand_name());
        tvGoodName.setText(itemsBean.getGoods_name());
        tvAdvert.setText(itemsBean.getPromotion_note());
        tvPreSold.setText(itemsBean.getShipping_str());
        Glide.with(this).load(itemsBean.getBrand_info().getBrand_logo()).into(ivBrandLogo);
        tvBuyTips.setText(itemsBean.getGood_guide().getContent());
        tvBrandDesc.setText(itemsBean.getBrand_info().getBrand_desc());
        tvLikeNumber.setText(itemsBean.getLike_count());
        tvGoodDesc.setText(itemsBean.getGoods_desc());
    }

    @OnClick({R.id.ll_brand_detail, R.id.iv_like, R.id.tv_choose_size, R.id.tv_after_sale_tips, R.id.iv_back, R.id.iv_cart, R.id.iv_call_center, R.id.tv_add_cart, R.id.tv_just_buy})
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.ll_brand_detail:
                Intent intent = new Intent(this, BrandDetailActivity.class);
                intent.putExtra("brand_id", itemsBean.getBrand_info().getBrand_id());
                startActivity(intent);
                break;
            case R.id.iv_like:
                break;
            case R.id.tv_choose_size:
                startBuyActivity("choose");
                break;
            case R.id.tv_after_sale_tips:
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_call_center:
                break;
            case R.id.tv_add_cart:
                startBuyActivity("add");

                break;
            case R.id.tv_just_buy:
                startBuyActivity("buy");

                break;
        }
    }

    private void startBuyActivity(String tag) {

        List<GoodsBean.DataBean.ItemsBean.SkuInfoBean> skuInfoBeans = itemsBean.getSku_info();

        BuyBean buyBean = new BuyBean();
        buyBean.setTag(tag);
        buyBean.setBrand(itemsBean.getBrand_info().getBrand_name());
        buyBean.setName(itemsBean.getGoods_name());
        buyBean.setDefaultImage(itemsBean.getGoods_image());
        buyBean.setSkuInfoBeans(skuInfoBeans);

        if (!TextUtils.isEmpty(itemsBean.getDiscount_price())) {
            buyBean.setPrice(itemsBean.getDiscount_price());
        } else {
            buyBean.setPrice(itemsBean.getPrice());
        }

        Intent intent = new Intent(this, BuyActivity.class);
        intent.putExtra("buy_bean", buyBean);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_in_bottom,R.anim.activity_in_alpha_1_1);

    }

    private class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context).load(path).into(imageView);
        }
    }
}
