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
import com.wgheng.myapp.mgz.bean.AuthorBean;
import com.wgheng.myapp.shop.adapter.ClassifyRecyclerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wgheng on 2017/7/6.
 */

public class AuthorRecyclerAdapter extends RecyclerView.Adapter<AuthorRecyclerAdapter.ViewHolder> {
    private final Context context;
    private final List<AuthorBean.DataBean.ItemsBean> itemsBeans;

    public AuthorRecyclerAdapter(Context context, List<AuthorBean.DataBean.ItemsBean> itemsBeans) {
        this.context = context;
        this.itemsBeans = itemsBeans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_author, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AuthorBean.DataBean.ItemsBean itemsBean = itemsBeans.get(position);
        holder.tvAuthor.setText(itemsBean.getAuthor_name());
        holder.tvDesc.setText(itemsBean.getNote());
        Glide.with(context).load(itemsBean.getThumb()).into(holder.ivAuthorLogo);
    }

    @Override
    public int getItemCount() {
        return itemsBeans == null ? 0 : itemsBeans.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_author_logo)
        ImageView ivAuthorLogo;
        @BindView(R.id.tv_author)
        TextView tvAuthor;
        @BindView(R.id.tv_desc)
        TextView tvDesc;

        ViewHolder(final View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClicked(view, getLayoutPosition());
                    }
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
