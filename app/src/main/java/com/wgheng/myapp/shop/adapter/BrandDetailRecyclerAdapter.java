package com.wgheng.myapp.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wgheng.myapp.R;
import com.wgheng.myapp.shop.bean.BrandDetailBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wgheng on 2017/7/6.
 */

public class BrandDetailRecyclerAdapter extends RecyclerView.Adapter<BrandDetailRecyclerAdapter.ViewHolder> {
    private final Context context;
    private final List<BrandDetailBean.DataBean.ItemsBean> itemsBeans;

    public BrandDetailRecyclerAdapter(Context context, List<BrandDetailBean.DataBean.ItemsBean> itemsBeans) {
        this.context = context;
        this.itemsBeans = itemsBeans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_classify_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BrandDetailBean.DataBean.ItemsBean itemsBean = itemsBeans.get(position);
        Glide.with(context).load(itemsBean.getGoods_image()).into(holder.iv);
        holder.tvGoodName.setText(itemsBean.getGoods_name());
        holder.tvBrandName.setText(itemsBean.getBrand_info().getBrand_name());
        holder.tvLikeNumber.setText(itemsBean.getLike_count());

        if (!TextUtils.isEmpty(itemsBean.getDiscount_price())) {
            holder.tvRealPrice.setText("￥" + itemsBean.getDiscount_price());
            holder.tvOriginPrice.setText("￥" + itemsBean.getPrice());
        } else {
            holder.tvRealPrice.setText("￥" + itemsBean.getPrice());
            holder.flOriginPrice.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return itemsBeans == null ? 0 : itemsBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_brand_name1)
        TextView tvGoodName;
        @BindView(R.id.tv_brand_name)
        TextView tvBrandName;
        @BindView(R.id.tv_like_number)
        TextView tvLikeNumber;
        @BindView(R.id.tv_real_price)
        TextView tvRealPrice;
        @BindView(R.id.tv_origin_price)
        TextView tvOriginPrice;
        @BindView(R.id.fl_origin_price)
        FrameLayout flOriginPrice;

        public ViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClicked(view, getLayoutPosition());
                }
            });
        }
    }

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClicked(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
