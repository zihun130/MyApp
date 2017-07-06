package com.wgheng.myapp.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wgheng.myapp.R;
import com.wgheng.myapp.shop.bean.BrandBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wgheng on 2017/7/6.
 */

public class BrandRecyclerAdapter extends RecyclerView.Adapter<BrandRecyclerAdapter.ViewHolder> {
    private final Context context;
    private final List<BrandBean.DataBean.ItemsBean> itemsBeans;


    public BrandRecyclerAdapter(Context context, List<BrandBean.DataBean.ItemsBean> itemsBeans) {
        this.context = context;
        this.itemsBeans = itemsBeans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_brand, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BrandBean.DataBean.ItemsBean itemsBean = itemsBeans.get(position);
        holder.tvBrandName.setText(itemsBean.getBrand_name());
        Glide.with(context).load(itemsBean.getBrand_logo()).into(holder.ivBrandLogo);
    }

    @Override
    public int getItemCount() {
        return itemsBeans == null ? 0 : itemsBeans.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_brand_logo)
        ImageView ivBrandLogo;
        @BindView(R.id.tv_brand_name)
        TextView tvBrandName;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
