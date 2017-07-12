package com.wgheng.myapp.shop.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wgheng.addsubview_lib.AddSubView;
import com.wgheng.myapp.R;
import com.wgheng.myapp.common.CartDataHelper;
import com.wgheng.myapp.shop.activity.CartActivity;
import com.wgheng.myapp.shop.bean.CartBean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wgheng on 2017/7/11.
 */

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    private final Context context;
    private final List<CartBean> cartBeans;
    private CartActivity cartActivity;

    public CartAdapter(Context context, List<CartBean> cartBeans) {
        this.context = context;
        this.cartBeans = cartBeans;
        cartActivity = (CartActivity) context;
    }

    public void refresh(List<CartBean> cartBeans) {
        this.cartBeans.clear();
        this.cartBeans.addAll(cartBeans);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        CartBean cartBean = cartBeans.get(position);

        //根据编辑状态设置视图显示效果
        if (CartActivity.isEdit) {
            holder.tvDelete.setVisibility(View.VISIBLE);
            holder.addSubView.setVisibility(View.VISIBLE);
            holder.addSubView.setValue(cartBean.getCount());

            holder.tvGoodsType.setVisibility(View.GONE);
            holder.tvOriginPrice.setVisibility(View.GONE);
            holder.tvCount.setVisibility(View.GONE);
        } else {
            holder.tvDelete.setVisibility(View.GONE);
            holder.addSubView.setVisibility(View.GONE);
            holder.tvGoodsType.setVisibility(View.VISIBLE);
            holder.tvOriginPrice.setVisibility(View.VISIBLE);
            holder.tvCount.setVisibility(View.VISIBLE);
        }

        Glide.with(context).load(cartBean.getImagePath()).into(holder.ivGoods);
        holder.tvGoodsName.setText(cartBean.getGoodsName());
        holder.tvRealPrice.setText("￥" + cartBean.getPrice());

        if (cartBean.getPrice().equals(cartBean.getOriginPrice())) {
            holder.tvOriginPrice.setVisibility(View.GONE);
        } else {
            holder.tvOriginPrice.setVisibility(View.VISIBLE);
            holder.tvOriginPrice.setText("￥" + cartBean.getOriginPrice());
            holder.tvOriginPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
        }

        holder.tvCount.setText("×" + cartBean.getCount());

        //解决视图复用（原理？）
        holder.cbItem.setOnCheckedChangeListener(null);
        holder.cbItem.setChecked(cartBean.isChecked());

        //遍历款式的集合获取信息
        HashMap<String, String> types = cartBean.getTypes();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : types.entrySet()) {
            sb.append(entry.getKey() + ":" + entry.getValue() + ";");
        }
        holder.tvGoodsType.setText(sb.toString());

        setListener(holder, position);

    }

    private void setListener(final ViewHolder holder, final int position) {

        final CartBean cartBean = CartDataHelper.getInstance().getCartBeans().get(position);

        //数量加减的监听
        holder.addSubView.setOnNumberChangedListener(new AddSubView.OnNumberChangedListener() {
            @Override
            public void onOnNumberChanged(int value) {
                //更新内存及数据库数据
                CartDataHelper.getInstance().updateData(position,value);
                //更新UI:数量
                holder.tvCount.setText(cartBean.getCount()+"");
                //更新UI：总价格（检测是否选中）
                cartActivity.computeTotalPrice();
            }
        });

        //checkbox监听
        holder.cbItem.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.d("tag", "onCheckedChanged: " + isChecked);
                //更新内存
                cartBean.setChecked(isChecked);
                //更新UI：总价
                cartActivity.computeTotalPrice();
                cartActivity.checkAll();
            }
        });

        //删除的监听
        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setTitle("确定要删除商品吗？")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                //更新内存及数据库数据
                                CartDataHelper.getInstance().removeData(cartBean);
                                //更新UI
                                notifyDataSetChanged();
                                cartActivity.checkAll();
                                //更新UI：价格
                                cartActivity.computeTotalPrice();

                            }
                        }).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return cartBeans == null ? 0 : cartBeans.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.cb_item)
        CheckBox cbItem;
        @BindView(R.id.iv_goods)
        ImageView ivGoods;
        @BindView(R.id.add_sub_view)
        AddSubView addSubView;
        @BindView(R.id.tv_goods_name)
        TextView tvGoodsName;
        @BindView(R.id.tv_goods_type)
        TextView tvGoodsType;
        @BindView(R.id.tv_real_price)
        TextView tvRealPrice;
        @BindView(R.id.tv_origin_price)
        TextView tvOriginPrice;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.tv_delete)
        TextView tvDelete;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
