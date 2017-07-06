package com.wgheng.myapp.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.wgheng.myapp.R;
import com.wgheng.myapp.shop.bean.HomeBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wgheng on 2017/7/6.
 * 主页RecyclerView分类型适配器
 */

public class HomeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String HOME_TYPE_ONE = "1";
    public static final String HOME_TYPE_TWO = "2";
    public static final String HOME_TYPE_THREE = "3";
    public static final String HOME_TYPE_FOUR = "4";
    private final Context context;
    private final List<HomeBean.DataBean.ItemsBean.ListBean> listBeans;


    public HomeRecyclerAdapter(Context context, List<HomeBean.DataBean.ItemsBean.ListBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        RecyclerView.ViewHolder holder = null;
        View view;
        switch (viewType) {
            case 1:
                view = LayoutInflater.from(context).inflate(R.layout.item_home_one_child, parent, false);
                holder = new ViewHolderOne(view);
                break;
            case 2:
                view = LayoutInflater.from(context).inflate(R.layout.item_home_two_child, parent, false);
                holder = new ViewHolderTwo(view);
                break;
            case 3:
            case 4:
                view = LayoutInflater.from(context).inflate(R.layout.item_home_stagger, parent, false);
                holder = new ViewHolderStagger(view);
                break;
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        HomeBean.DataBean.ItemsBean.ListBean listBean = listBeans.get(position);
        int viewType = getItemViewType(position);
        switch (viewType) {
            case 1:
                ViewHolderOne holderOne = (ViewHolderOne) holder;
                Glide.with(context).load(listBean.getOne().getPic_url()).into(((ViewHolderOne) holder).iv);
                break;
            case 2:
                break;
            case 3:
            case 4:
                break;
        }
    }

    @Override
    public int getItemCount() {
        return listBeans == null ? 0 : listBeans.size();
    }

    @Override
    public int getItemViewType(int position) {
        HomeBean.DataBean.ItemsBean.ListBean listBean = listBeans.get(position);
        String type = listBean.getHome_type();

        int viewType = 0;
        if (HOME_TYPE_ONE.equals(type)) {
            viewType = 1;
        } else if (HOME_TYPE_TWO.equals(type)) {
            viewType = 2;
        } else if (HOME_TYPE_THREE.equals(type)) {
            viewType = 3;
        } else if (HOME_TYPE_FOUR.equals(type)) {
            viewType = 4;
        }

        return viewType;
    }

    static class ViewHolderOne extends RecyclerView.ViewHolder {

        @BindView(R.id.iv)
        ImageView iv;

        public ViewHolderOne(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    static class ViewHolderTwo extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_two_child)
        LinearLayout llTwoChild;

        ViewHolderTwo(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolderStagger extends RecyclerView.ViewHolder {
        @BindView(R.id.recycler_stagger)
        RecyclerView recyclerStagger;

        ViewHolderStagger(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
