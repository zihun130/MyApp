package com.wgheng.myapp.shop.activity;

import android.support.annotation.IdRes;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseActivity;
import com.wgheng.myapp.view.PullUpToLoadMore;
import com.youth.banner.Banner;

import butterknife.BindView;
import butterknife.OnClick;

public class GoodsActivity extends BaseActivity {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.tv_good_name)
    TextView tvGoodName;
    @BindView(R.id.tv_good_detail)
    TextView tvGoodDetail;
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
    @BindView(R.id.iv_pre_sold)
    TextView ivPreSold;
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
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    @BindView(R.id.tv_good_desc)
    TextView tvGoodDesc;
    @BindView(R.id.tv_brand_name_too)
    TextView tvBrandNameToo;
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

    @Override
    public int getLayoutId() {
        return R.layout.activity_goods;
    }

    @Override
    protected void initView() {
        super.initView();
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
        return super.getUrl();
    }

    @Override
    protected void initData() {
        super.initData();
        rgBrand.check(R.id.rb_good_info);
    }

    @OnClick({R.id.iv_like, R.id.tv_choose_size, R.id.tv_after_sale_tips, R.id.iv_back, R.id.iv_cart, R.id.iv_call_center, R.id.tv_add_cart, R.id.tv_just_buy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_like:
                break;
            case R.id.tv_choose_size:
                break;
            case R.id.tv_after_sale_tips:
                break;
            case R.id.iv_back:
                break;
            case R.id.iv_call_center:
                break;
            case R.id.tv_add_cart:
                break;
            case R.id.tv_just_buy:
                break;
        }
    }
}
