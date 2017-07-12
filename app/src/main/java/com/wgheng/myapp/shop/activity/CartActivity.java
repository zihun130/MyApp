package com.wgheng.myapp.shop.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseActivity;
import com.wgheng.myapp.common.CartDataHelper;
import com.wgheng.myapp.shop.adapter.CartAdapter;
import com.wgheng.myapp.shop.bean.CartBean;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class CartActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_edit)
    TextView tvEdit;
    @BindView(R.id.tv_complete)
    TextView tvComplete;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.tv_full_reduction)
    TextView tvFullReduction;
    @BindView(R.id.tv_discount)
    TextView tvDiscount;
    @BindView(R.id.tv_package_cost)
    TextView tvPackageCost;
    @BindView(R.id.tv_ship_cost)
    TextView tvShipCost;
    @BindView(R.id.cb_check_all)
    CheckBox cbCheckAll;
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    @BindView(R.id.tv_saving_money)
    TextView tvSavingMoney;
    @BindView(R.id.tv_settle)
    TextView tvSettle;
    private CartAdapter adapter;
    public static boolean isEdit = false;
    private boolean isCheckAll = true;

    @Override
    public int getLayoutId() {
        return R.layout.activity_cart;
    }


    @Override
    protected void initData() {
        super.initData();
        //设置初始均为选中状态
        setCheckedStatus(true);

        adapter = new CartAdapter(this, CartDataHelper.getInstance().getCartBeans());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        computeTotalPrice();

        cbCheckAll.setChecked(true);
    }

    private void setCheckedStatus(boolean allStatus) {
        for (CartBean cartBean : CartDataHelper.getInstance().getCartBeans()) {
            cartBean.setChecked(allStatus);
        }
    }

    @OnClick({R.id.iv_back, R.id.tv_edit, R.id.tv_complete, R.id.cb_check_all, R.id.tv_settle})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_edit:
                tvEdit.setVisibility(View.GONE);
                tvComplete.setVisibility(View.VISIBLE);
                isEdit = !isEdit;
                setCheckedStatus(false);
                adapter.notifyDataSetChanged();
                checkAll();
                computeTotalPrice();
                break;
            case R.id.tv_complete:
                tvEdit.setVisibility(View.VISIBLE);
                tvComplete.setVisibility(View.GONE);
                isEdit = !isEdit;
                setCheckedStatus(true);
                adapter.notifyDataSetChanged();
                checkAll();
                computeTotalPrice();
                break;
            case R.id.cb_check_all:
                isCheckAll = !isCheckAll;
                List<CartBean> cartBeans = CartDataHelper.getInstance().getCartBeans();
                for (int i = 0; i < cartBeans.size(); i++) {
                    cartBeans.get(i).setChecked(isCheckAll);
                }
                adapter.notifyDataSetChanged();
                computeTotalPrice();
                break;
            case R.id.tv_settle:
                break;
        }
    }

    public void computeTotalPrice() {
        List<CartBean> cartBeans = CartDataHelper.getInstance().getCartBeans();
        double price = 0;
        double originPrice = 0;
        for (int i = 0; i < cartBeans.size(); i++) {
            CartBean cartBean = cartBeans.get(i);
            if (cartBean.isChecked()) {
                price += Double.parseDouble(cartBean.getPrice()) * cartBean.getCount();
                originPrice += Double.parseDouble(cartBean.getOriginPrice()) * cartBean.getCount();
            }
        }
        //设置视图数据
        updateTotalPrice(price, originPrice - price);
    }

    public void updateTotalPrice(double price, double discount) {

        tvTotalMoney.setText("总计:￥" + String.format("%.2f", price));
        tvDiscount.setText("-￥" + String.format("%.2f", discount));
        tvSavingMoney.setText("已节省:￥" + String.format("%.2f", discount));

        if (discount != 0) {
            tvSavingMoney.setVisibility(View.VISIBLE);
        } else {
            tvSavingMoney.setVisibility(View.GONE);
        }
    }

    public void checkAll() {

        boolean allChecked = true;
        List<CartBean> cartBeans = CartDataHelper.getInstance().getCartBeans();
        for (int i = 0; i < cartBeans.size(); i++) {
            if (!cartBeans.get(i).isChecked()) {
                allChecked = false;
            }
        }
        cbCheckAll.setChecked(allChecked);
        isCheckAll = allChecked;
    }

}
