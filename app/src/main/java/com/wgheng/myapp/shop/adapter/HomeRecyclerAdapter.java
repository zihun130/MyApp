package com.wgheng.myapp.shop.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.wgheng.myapp.shop.bean.HomeBean;

import java.util.List;

/**
 * Created by wgheng on 2017/7/6.
 */

public class HomeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private final Context context;
    private final List<HomeBean.DataBean.ItemsBean.ListBean> listBeen;

    public HomeRecyclerAdapter(Context context, List<HomeBean.DataBean.ItemsBean.ListBean> listBeen) {
        this.context = context;
        this.listBeen = listBeen;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

}
