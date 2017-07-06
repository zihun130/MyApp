package com.wgheng.myapp.dazen.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wgheng.myapp.R;
import com.wgheng.myapp.dazen.bean.DazenBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wgheng on 2017/7/6.
 */

public class DarenRecyclerAdapter extends RecyclerView.Adapter<DarenRecyclerAdapter.ViewHolder> {
    private final Context context;
    private final List<DazenBean.DataBean.ItemsBean> itemsBeans;

    public DarenRecyclerAdapter(Context context, List<DazenBean.DataBean.ItemsBean> itemsBeans) {
        this.context = context;
        this.itemsBeans = itemsBeans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_daren, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        DazenBean.DataBean.ItemsBean itemsBean = itemsBeans.get(position);
        holder.tvUsername.setText(itemsBean.getUsername());
        holder.tvDuty.setText(itemsBean.getDuty());
        Glide.with(context)
                .load(itemsBean.getUser_images().getOrig())
                .into(holder.ivOrig);
    }

    @Override
    public int getItemCount() {
        return itemsBeans == null ? 0 : itemsBeans.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_orig)
        ImageView ivOrig;
        @BindView(R.id.tv_username)
        TextView tvUsername;
        @BindView(R.id.tv_duty)
        TextView tvDuty;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
