package com.wgheng.myapp.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.wgheng.myapp.R;
import com.wgheng.myapp.shop.bean.ClassifyBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wgheng on 2017/7/6.
 */

public class ClassifyRecyclerAdapter extends RecyclerView.Adapter<ClassifyRecyclerAdapter.ViewHolder> {
    private final Context context;
    private final List<ClassifyBean.DataBean.ItemsBean> itemsBeans;

    public ClassifyRecyclerAdapter(Context context, List<ClassifyBean.DataBean.ItemsBean> itemsBeans) {
        this.context = context;
        this.itemsBeans = itemsBeans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_classify, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ClassifyBean.DataBean.ItemsBean itemsBean = itemsBeans.get(position);
        Glide.with(context).load(itemsBean.getCover_new_img()).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return itemsBeans == null ? 0 : itemsBeans.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv)
        ImageView iv;

        public ViewHolder(final View view) {
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

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClicked(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
