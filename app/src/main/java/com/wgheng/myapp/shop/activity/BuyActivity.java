package com.wgheng.myapp.shop.activity;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wgheng.addsubview_lib.AddSubView;
import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseActivity;
import com.wgheng.myapp.shop.bean.BuyBean;
import com.wgheng.myapp.shop.bean.GoodsBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.functions.Predicate;

public class BuyActivity extends BaseActivity {

    @BindView(R.id.tv_brand_name)
    TextView tvBrandName;
    @BindView(R.id.tv_good_name)
    TextView tvGoodName;
    @BindView(R.id.tv_good_price)
    TextView tvGoodPrice;
    @BindView(R.id.ll_flow_container)
    LinearLayout llFlowContainer;
    @BindView(R.id.add_sub_view)
    AddSubView addSubView;
    @BindView(R.id.iv_good)
    ImageView ivGood;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_confirm)
    TextView tvConfirm;
    @BindView(R.id.tv_add_cart)
    TextView tvAddCart;
    @BindView(R.id.tv_buy)
    TextView tvBuy;
    @BindView(R.id.ll_add_or_buy)
    LinearLayout llAddOrBuy;
    private View typeView;
    private BuyBean buyBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_buy;
    }

    @Override
    protected void initData() {
        super.initData();

        buyBean = (BuyBean) getIntent().getSerializableExtra("buy_bean");

        tvBrandName.setText(buyBean.getBrand());
        tvGoodName.setText(buyBean.getName());
        tvGoodPrice.setText("￥" + buyBean.getPrice());

        initFlowLayout();

    }

    //添加流式布局
    private void initFlowLayout() {
        Observable.fromIterable(buyBean.getSkuInfoBeans())
                .filter(new Predicate<GoodsBean.DataBean.ItemsBean.SkuInfoBean>() {
                    @Override
                    public boolean test(@NonNull GoodsBean.DataBean.ItemsBean.SkuInfoBean skuInfoBean) throws Exception {
                        return skuInfoBean.getAttrList() != null && skuInfoBean.getAttrList().size() > 0;
                    }
                })
                .flatMap(new Function<GoodsBean.DataBean.ItemsBean.SkuInfoBean, Observable<List<GoodsBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean>>>() {
                    @Override
                    public Observable<List<GoodsBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean>> apply(@NonNull GoodsBean.DataBean.ItemsBean.SkuInfoBean skuInfoBean) throws Exception {
                        //动态添加布局
                        typeView = View.inflate(BuyActivity.this, R.layout.buy_type_flow, null);
                        TextView flowName = (TextView) typeView.findViewById(R.id.tv_flow_name);
                        flowName.setText(skuInfoBean.getType_name());
                        llFlowContainer.addView(typeView);

                        return Observable.just(skuInfoBean.getAttrList());
                    }
                })
                .subscribe(new Consumer<List<GoodsBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean>>() {
                    @Override
                    public void accept(@NonNull List<GoodsBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean> attrListBeen) throws Exception {
                        TagFlowLayout flowLayout = (TagFlowLayout) typeView.findViewById(R.id.flow_layout);
                        setFlowViewData(flowLayout, attrListBeen);
                    }
                });
    }


    //设置流失布局数据
    private void setFlowViewData(TagFlowLayout flowLayout, final List<GoodsBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean> attrListBean) {

        TagAdapter adapter = new TagAdapter(attrListBean) {
            @Override
            public View getView(FlowLayout parent, int position, Object o) {
                TextView textView = (TextView) View.inflate(BuyActivity.this, R.layout.item_flow_layout, null);
                textView.setText(attrListBean.get(position).getAttr_name());
                return textView;
            }
        };

        flowLayout.setAdapter(adapter);

        flowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {

                if (!TextUtils.isEmpty(attrListBean.get(position).getImg_path())) {
                    Glide.with(BuyActivity.this).load(attrListBean.get(position).getImg_path()).into(ivGood);
                }

                return true;
            }
        });

        //设置默认选择
        adapter.setSelectedList(0);
        if (!TextUtils.isEmpty(attrListBean.get(0).getImg_path())) {
            Glide.with(BuyActivity.this).load(attrListBean.get(0).getImg_path()).into(ivGood);
        } else {
            Glide.with(BuyActivity.this).load(buyBean.getDefaultImage()).into(ivGood);
        }
    }

    @OnClick({R.id.iv_back, R.id.tv_confirm, R.id.tv_add_cart, R.id.tv_buy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finishActivity();
                break;
            case R.id.tv_confirm:
                break;
            case R.id.tv_add_cart:
                break;
            case R.id.tv_buy:
                break;
        }
    }

    private void finishActivity() {
        finish();
        overridePendingTransition(R.anim.activity_in_alpha_0_1, R.anim.activity_out_bottom);
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finishActivity();
            return true;
        }

        return super.onKeyUp(keyCode, event);
    }
}
