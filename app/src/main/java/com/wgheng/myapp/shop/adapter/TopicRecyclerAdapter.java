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
import com.wgheng.myapp.shop.bean.TopicBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wgheng on 2017/7/6.
 */

public class TopicRecyclerAdapter extends RecyclerView.Adapter<TopicRecyclerAdapter.ViewHolder> {
    private final Context context;
    private final List<TopicBean.DataBean.ItemsBean> itemsBeans;

    public TopicRecyclerAdapter(Context context, List<TopicBean.DataBean.ItemsBean> itemsBeans) {
        this.context = context;
        this.itemsBeans = itemsBeans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_topic, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TopicBean.DataBean.ItemsBean itemsBean = itemsBeans.get(position);
        holder.tvTopicName.setText(itemsBean.getTopic_name());
        Glide.with(context).load(itemsBean.getCover_img()).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return itemsBeans == null ? 0 : itemsBeans.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_topic_name)
        TextView tvTopicName;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
