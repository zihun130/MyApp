package com.wgheng.myapp.mgz.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.wgheng.myapp.R;
import com.wgheng.myapp.mgz.bean.MGZBean;
import com.wgheng.myapp.shop.adapter.ClassifyRecyclerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wgheng on 2017/7/6.
 */

public class MGZRecyclerAdapter extends RecyclerView.Adapter<MGZRecyclerAdapter.ViewHolder> {
    private final Context context;
    private final List<MGZBean> mgzBeans;

    public MGZRecyclerAdapter(Context context, List<MGZBean> mgzBeans) {
        this.context = context;
        this.mgzBeans = mgzBeans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_mgz, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MGZBean itemsBean = mgzBeans.get(position);
        holder.tvTopicName.setText(itemsBean.getTopic_name());
        holder.tvCatName.setText("— "+itemsBean.getCat_name()+" —");
        holder.tvMonth.setText("— " + itemsBean.getMonthInfo() + " —");
        Glide.with(context).load(itemsBean.getCover_img()).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return mgzBeans == null ? 0 : mgzBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;
        @BindView(R.id.tv_topic_name)
        TextView tvTopicName;
        @BindView(R.id.tv_cat_name)
        TextView tvCatName;
        @BindView(R.id.tv_month)
        TextView tvMonth;

        ViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClicked(view,getLayoutPosition());
                }
            });
        }
    }

    private ClassifyRecyclerAdapter.OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClicked(View view, int layoutPosition);
    }

    public void setOnItemClickListener(ClassifyRecyclerAdapter.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
