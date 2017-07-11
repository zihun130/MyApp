package com.wgheng.myapp.shop.activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.wgheng.addsubview_lib.AddSubView;
import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseActivity;
import com.wgheng.myapp.common.CartDataHelper;
import com.wgheng.myapp.shop.bean.BuyBean;
import com.wgheng.myapp.shop.bean.CartBean;
import com.wgheng.myapp.shop.bean.GoodsBean;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
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
    private BuyBean buyBean;
    private TagFlowLayout flowLayout;
    private List<TagFlowLayout> flowLayouts = new ArrayList<>();
    private int count = 1;
    private String imagePath;

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

        if (buyBean.getTag().equals(GoodsActivity.CHOOSE)) {
            tvConfirm.setVisibility(View.GONE);
            llAddOrBuy.setVisibility(View.VISIBLE);
        } else if (buyBean.getTag().equals(GoodsActivity.ADD) || buyBean.getTag().equals(GoodsActivity.BUY)) {
            tvConfirm.setVisibility(View.VISIBLE);
            llAddOrBuy.setVisibility(View.GONE);
        }

        initFlowLayout();

        setAddSubViewListener();

    }

    private void setAddSubViewListener() {
        addSubView.setOnNumberChangedListener(new AddSubView.OnNumberChangedListener() {
            @Override
            public void onOnNumberChanged(int value) {
                count = value;
            }
        });
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
                        View typeView = View.inflate(BuyActivity.this, R.layout.buy_type_flow, null);
                        TextView flowName = (TextView) typeView.findViewById(R.id.tv_flow_name);
                        flowLayout = (TagFlowLayout) typeView.findViewById(R.id.flow_layout);

                        //给每一个创建DataHolder用于存储选择的数据
                        DataHolder dataHolder = new DataHolder();
                        dataHolder.typeId = skuInfoBean.getType_id();
                        dataHolder.typeName = skuInfoBean.getType_name();
                        dataHolder.isColor = skuInfoBean.getIsColor();
                        flowLayout.setTag(dataHolder);

                        flowName.setText(skuInfoBean.getType_name());
                        llFlowContainer.addView(typeView);

                        flowLayouts.add(flowLayout);

                        return Observable.just(skuInfoBean.getAttrList());
                    }
                })
                .subscribe(new Consumer<List<GoodsBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean>>() {
                    @Override
                    public void accept(@NonNull List<GoodsBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean> attrListBeen) throws Exception {
                        setFlowViewData(attrListBeen);
                    }
                });
    }


    //设置流失布局数据
    private void setFlowViewData(final List<GoodsBean.DataBean.ItemsBean.SkuInfoBean.AttrListBean> attrListBean) {

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

                DataHolder holder = (DataHolder) parent.getTag();

                //改变图片链接
                if ("1".equals(holder.isColor)) {
                    imagePath = attrListBean.get(position).getImg_path();
                    Glide.with(BuyActivity.this).load(imagePath).into(ivGood);
                }

                holder.attrId = attrListBean.get(position).getAttr_id();
                holder.attrName = attrListBean.get(position).getAttr_name();

                return true;
            }
        });

        //设置默认选择
        adapter.setSelectedList(0);

        imagePath = attrListBean.get(0).getImg_path();

        if (TextUtils.isEmpty(imagePath)) {
            imagePath = buyBean.getDefaultImage();
        }

        Glide.with(BuyActivity.this).load(imagePath).into(ivGood);

        DataHolder holder = (DataHolder) flowLayout.getTag();
        holder.attrId = attrListBean.get(0).getAttr_id();
        holder.attrName = attrListBean.get(0).getAttr_name();

        Log.d("flowLayout", flowLayout.getTag().toString());
    }

    @OnClick({R.id.iv_back, R.id.tv_confirm, R.id.tv_add_cart, R.id.tv_buy})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finishActivity();
                break;

            //两个逻辑一样
            case R.id.tv_confirm:
            case R.id.tv_add_cart:
                confirmData();
                break;
            case R.id.tv_buy:
                break;
        }
    }

    //将要提交的数据信息包装到CartBean类中
    private void confirmData() {

        CartBean cartBean = new CartBean();

        cartBean.setGoodsName(buyBean.getName());
        cartBean.setPrice(buyBean.getPrice());
        cartBean.setCount(count + "");
        cartBean.setImagePath(imagePath);
        cartBean.setGoodsPath(buyBean.getGoodsPath());
        cartBean.setOriginPrice(buyBean.getOriginPrice());

        //为数据库主键预留字段
        String key = buyBean.getName();
        //存放不同类型信息的集合
        HashMap<String, String> types = new HashMap<>();
        for (int i = 0; i < flowLayouts.size(); i++) {
            DataHolder holder = (DataHolder) flowLayouts.get(i).getTag();
            key = key + holder.typeId + holder.attrId;
            types.put(holder.typeName, holder.attrName);
        }

        cartBean.setKey(key);
        cartBean.setTypes(types);

        //添加到购物车数据集合中
        CartDataHelper.getInstance().getCartBeans().add(cartBean);
        //添加数据到数据库
        CartDataHelper.getInstance().addToDB(cartBean);
        Toast.makeText(BuyActivity.this, "添加到购物车", Toast.LENGTH_SHORT).show();
        finishActivity();
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

    static class DataHolder {

        String attrId;
        String attrName;
        String typeId;
        String typeName;
        String isColor;

    }
}
