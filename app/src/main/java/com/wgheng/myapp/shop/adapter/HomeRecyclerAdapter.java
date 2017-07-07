package com.wgheng.myapp.shop.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.wgheng.myapp.R;
import com.wgheng.myapp.shop.activity.WebActivity;
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
        final HomeBean.DataBean.ItemsBean.ListBean listBean = listBeans.get(position);
        int viewType = getItemViewType(position);
        switch (viewType) {
            case 1:
                final ViewHolderOne holderOne = (ViewHolderOne) holder;
                Glide.with(context).load(listBean.getOne().getPic_url()).into(holderOne.iv);
                setOnclickListener(holderOne.iv, listBean.getOne().getTopic_name(), listBean.getOne().getTopic_url());
                break;
            case 2:
                ViewHolderTwo holderTwo = (ViewHolderTwo) holder;
                Glide.with(context).load(listBean.getOne().getPic_url()).into(holderTwo.iv1);
                Glide.with(context).load(listBean.getTwo().getPic_url()).into(holderTwo.iv2);
                setOnclickListener(holderTwo.iv1,listBean.getOne().getTopic_name(),listBean.getOne().getTopic_url());
                setOnclickListener(holderTwo.iv2,listBean.getTwo().getTopic_name(),listBean.getOne().getTopic_url());
                break;
            case 3:
            case 4:
                ViewHolderStagger holderStagger = (ViewHolderStagger) holder;
                holderStagger.setData(listBean);
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

    private void setOnclickListener(ImageView iv, final String title, final String url) {
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("url", url);
                context.startActivity(intent);
            }
        });
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

        @BindView(R.id.iv1)
        ImageView iv1;
        @BindView(R.id.iv2)
        ImageView iv2;

        ViewHolderTwo(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    class ViewHolderStagger extends RecyclerView.ViewHolder {
        @BindView(R.id.ll_left)
        LinearLayout llLeft;
        @BindView(R.id.ll_right)
        LinearLayout llRight;

        ViewHolderStagger(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(final HomeBean.DataBean.ItemsBean.ListBean listBean) {

            llLeft.removeAllViews();
            llRight.removeAllViews();

            ImageView iv1 = getImageView();
            setOnclickListener(iv1, listBean.getOne().getTopic_name(), listBean.getOne().getTopic_url());
            ImageView iv2 = getImageView();
            setOnclickListener(iv2, listBean.getTwo().getTopic_name(), listBean.getOne().getTopic_url());
            ImageView iv3 = getImageView();
            setOnclickListener(iv3, listBean.getThree().getTopic_name(), listBean.getOne().getTopic_url());


            Glide.with(context).load(listBean.getOne().getPic_url()).into(iv1);
            Glide.with(context).load(listBean.getTwo().getPic_url()).into(iv2);
            Glide.with(context).load(listBean.getThree().getPic_url()).into(iv3);

            llLeft.addView(iv1);
            llLeft.addView(iv2);
            llRight.addView(iv3);

            if (listBean.getFour() != null) {
                ImageView iv4 = getImageView();
                setOnclickListener(iv4, listBean.getFour().getTopic_name(), listBean.getOne().getTopic_url());
                Glide.with(context).load(listBean.getFour().getPic_url()).into(iv4);
                llRight.addView(iv4);
            }

        }

        private ImageView getImageView() {
            ImageView imageView = new ImageView(context);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            imageView.setLayoutParams(params);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            return imageView;
        }
    }
}
