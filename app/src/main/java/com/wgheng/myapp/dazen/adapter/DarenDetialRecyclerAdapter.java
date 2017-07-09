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
import com.wgheng.myapp.dazen.activity.DarenDetailActivity;
import com.wgheng.myapp.dazen.bean.DarenDetialBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wgheng on 2017/7/6.
 */

public class DarenDetialRecyclerAdapter extends RecyclerView.Adapter<DarenDetialRecyclerAdapter.ViewHolder> {
    private Context context;
    private DarenDetialBean.DataBean.ItemsBean itemsBeans;
    private String type;


    public DarenDetialRecyclerAdapter(Context context, String type, DarenDetialBean.DataBean.ItemsBean itemsBean) {
        this.type = type;
        this.context = context;
        this.itemsBeans = itemsBean;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_daren, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvUsername.setVisibility(View.GONE);
        holder.tvDuty.setVisibility(View.GONE);

        switch (type) {
            case DarenDetailActivity.LIKE:
            case DarenDetailActivity.RECOMMEND:

                DarenDetialBean.DataBean.ItemsBean.GoodsBean goodsBean = itemsBeans.getGoods().get(position);
                Glide.with(context).load(goodsBean.getGoods_image()).into(holder.ivOrig);

                break;
            case DarenDetailActivity.MARK:
                holder.tvUsername.setVisibility(View.VISIBLE);
                holder.tvDuty.setVisibility(View.VISIBLE);

                DarenDetialBean.DataBean.ItemsBean.UsersBean usersBean1 = itemsBeans.getUsers().get(position);
                Glide.with(context).load(usersBean1.getUser_image().getOrig()).into(holder.ivOrig);
                holder.tvUsername.setText(usersBean1.getUser_name());
                holder.tvDuty.setText(usersBean1.getUser_desc());

                break;
            case DarenDetailActivity.FANS:
                holder.tvUsername.setVisibility(View.VISIBLE);
                DarenDetialBean.DataBean.ItemsBean.UsersBean usersBean2 = itemsBeans.getUsers().get(position);
                Glide.with(context).load(usersBean2.getUser_image().getOrig()).into(holder.ivOrig);
                holder.tvUsername.setText(usersBean2.getUser_name());

                break;
        }
    }

    @Override
    public int getItemCount() {
        if (DarenDetailActivity.LIKE.equals(type) || DarenDetailActivity.RECOMMEND.equals(type)) {
            return itemsBeans.getGoods() == null ? 0 : itemsBeans.getGoods().size();
        } else if (DarenDetailActivity.MARK.equals(type) || DarenDetailActivity.FANS.equals(type)) {
            return itemsBeans.getUsers() == null ? 0 : itemsBeans.getUsers().size();
        }
        return 0;
    }

    public void changDataType(String type, DarenDetialBean.DataBean.ItemsBean itemsBeans) {
        this.type = type;
        this.itemsBeans = itemsBeans;
        notifyDataSetChanged();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_orig)
        ImageView ivOrig;
        @BindView(R.id.tv_username)
        TextView tvUsername;
        @BindView(R.id.tv_duty)
        TextView tvDuty;

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

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClicked(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
