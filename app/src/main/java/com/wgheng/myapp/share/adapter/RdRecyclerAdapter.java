package com.wgheng.myapp.share.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wgheng.myapp.R;
import com.wgheng.myapp.share.activity.CommendsActivity;
import com.wgheng.myapp.share.bean.RdBean;
import com.wgheng.myapp.shop.activity.WebActivity;
import com.wgheng.myapp.utils.CropTopTransformation;
import com.wgheng.myapp.utils.DensityUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

/**
 * Created by wgheng on 2017/7/12.
 */

public class RdRecyclerAdapter extends RecyclerView.Adapter<RdRecyclerAdapter.BaseHolder> {

    public static final int TYPE_VIDEO = 1;
    public static final int TYPE_GIF = 2;
    public static final int TYPE_IMAGE = 3;
    public static final int TYPE_HTML = 4;
    public static final int TYPE_TEXT = 5;
    private final Context context;
    private final List<RdBean.ListBean> listBeans;
    private int itemWidth;

    public RdRecyclerAdapter(Context context, List<RdBean.ListBean> listBeans) {
        this.context = context;
        this.listBeans = listBeans;
    }

    public void refresh(List<RdBean.ListBean> listBeans) {
        this.listBeans.clear();
        this.listBeans.addAll(listBeans);
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        RdBean.ListBean listBean = listBeans.get(position);
        String type = listBean.getType();
        int viewType = 0;
        switch (type) {
            case "video":
                viewType = TYPE_VIDEO;
                break;
            case "gif":
                viewType = TYPE_GIF;
                break;
            case "image":
                viewType = TYPE_IMAGE;
                break;
            case "html":
                viewType = TYPE_HTML;
                break;
            case "text":
                viewType = TYPE_TEXT;
                break;
        }

        return viewType;
    }

    @Override
    public BaseHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //获取item宽度
        itemWidth = parent.getWidth();

        LayoutInflater inflater = LayoutInflater.from(context);
        View view;
        BaseHolder holder = null;
        switch (viewType) {
            case TYPE_VIDEO:
                view = inflater.inflate(R.layout.item_video, parent, false);
                holder = new VideoHolder(view);
                break;
            case TYPE_GIF:
                view = inflater.inflate(R.layout.item_gif, parent, false);
                holder = new GifHolder(view);
                break;
            case TYPE_IMAGE:
                view = inflater.inflate(R.layout.item_image, parent, false);
                holder = new ImageHolder(view);
                break;
            case TYPE_HTML:
                view = inflater.inflate(R.layout.item_html, parent, false);
                holder = new HtmlHolder(view);
                break;
            case TYPE_TEXT:
                view = inflater.inflate(R.layout.item_text, parent, false);
                holder = new BaseHolder(view);
                break;
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(BaseHolder holder, int position) {

        final RdBean.ListBean listBean = listBeans.get(position);

        Glide.with(context).load(listBean.getU().getHeader().get(0)).into(holder.ivUserIcon);
        holder.tvUsername.setText(listBean.getU().getName());
        holder.tvTime.setText(listBean.getPasstime());
        holder.ivV.setVisibility(listBean.getU().isIs_v() ? View.VISIBLE : View.GONE);
        holder.ivVip.setVisibility(listBean.getU().isIs_vip() ? View.VISIBLE : View.GONE);
        holder.tvDing.setText(listBean.getUp());
        holder.tvCai.setText(listBean.getDown() + "");
        holder.tvShare.setText(listBean.getForward() + "");
        holder.tvCommend.setText(listBean.getComment());

        //设置评论部分
        List<RdBean.ListBean.TopCommentsBean> topComments = listBean.getTop_comments();
        holder.llTopCommends.setVisibility(topComments == null || topComments.size() == 0 ? View.GONE : View.VISIBLE);

        if (topComments != null) {

            holder.llTopCommends.removeAllViews();

            for (int i = 0; i < topComments.size(); i++) {
                TextView textView = new TextView(context);
                textView.setTextSize(15);

                //设置同一个textview不同颜色字体
                String comment = "<font color='#5271af'>" + topComments.get(i).getU().getName()
                        + "<font color='#616161'> : " + topComments.get(i).getContent() + "</font>";

                textView.setText(Html.fromHtml(comment));
                holder.llTopCommends.addView(textView);
            }
        }
        int viewType = getItemViewType(position);

        switch (viewType) {
            case TYPE_VIDEO:
                VideoHolder videoHolder = (VideoHolder) holder;
                videoHolder.tvTitle.setText(listBean.getText());
                videoHolder.JCPlayer.setUp(listBean.getVideo().getVideo().get(0),
                        JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
                Glide.with(context)
                        .load(listBean.getVideo().getThumbnail().get(0))
                        .into(videoHolder.JCPlayer.thumbImageView);
                break;
            case TYPE_GIF:
                GifHolder gifHolder = (GifHolder) holder;
                gifHolder.tvTitle.setText(listBean.getText());
                Glide.with(context)
                        .load(listBean.getGif().getImages().get(0))
                        .apply(new RequestOptions()
                                .override(DensityUtil.dip2px(context, listBean.getGif().getWidth()),
                                        DensityUtil.dip2px(context, listBean.getGif().getHeight())))
                        .into(gifHolder.ivGif);

                gifHolder.ivGif.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startCommendActivity(listBean);
                    }
                });

                break;
            case TYPE_IMAGE:
                ImageHolder imageHolder = (ImageHolder) holder;
                imageHolder.tvTitle.setText(listBean.getText());
                Glide.with(context)
                        .load(listBean.getImage().getBig().get(0))
                        .apply(new RequestOptions()
                                .bitmapTransform(new CropTopTransformation(context, itemWidth, itemWidth, CropTopTransformation.CropType.TOP)))
                        .into(imageHolder.ivImage);

                imageHolder.ivImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startCommendActivity(listBean);
                    }
                });

                break;
            case TYPE_HTML:
                HtmlHolder htmlHolder = (HtmlHolder) holder;
                htmlHolder.tvTitle.setText(listBean.getHtml().getTitle());
                Glide.with(context).load(listBean.getHtml().getThumbnail()).into(htmlHolder.ivHtml);

                htmlHolder.ivHtml.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, WebActivity.class);
                        intent.putExtra("url", listBean.getHtml().getSource_url());
                        context.startActivity(intent);
                    }
                });

                break;

            case TYPE_TEXT:
                holder.tvTitle.setText(listBean.getText());
                break;
        }

        setListener(holder, listBean);
    }

    private void setListener(BaseHolder holder, final RdBean.ListBean listBean) {

        //设置评论点击监听
        holder.llItemCommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCommendActivity(listBean);
            }
        });

        holder.tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCommendActivity(listBean);
            }
        });

    }

    private void startCommendActivity(RdBean.ListBean listBean) {
        Intent intent = new Intent(context, CommendsActivity.class);
        intent.putExtra("listBean", listBean);
        context.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        return listBeans == null ? 0 : listBeans.size();
    }


    static class BaseHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_user_icon)
        CircleImageView ivUserIcon;
        @BindView(R.id.iv_v)
        ImageView ivV;
        @BindView(R.id.tv_username)
        TextView tvUsername;
        @BindView(R.id.iv_vip)
        ImageView ivVip;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.iv_ding)
        ImageView ivDing;
        @BindView(R.id.tv_ding)
        TextView tvDing;
        @BindView(R.id.iv_cai)
        ImageView ivCai;
        @BindView(R.id.tv_cai)
        TextView tvCai;
        @BindView(R.id.iv_share)
        ImageView ivShare;
        @BindView(R.id.tv_share)
        TextView tvShare;
        @BindView(R.id.iv_commend)
        ImageView ivCommend;
        @BindView(R.id.tv_commend)
        TextView tvCommend;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.ll_top_commends)
        LinearLayout llTopCommends;
        @BindView(R.id.ll_item_share)
        LinearLayout llItemShare;
        @BindView(R.id.ll_item_commend)
        LinearLayout llItemCommend;

        BaseHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class VideoHolder extends BaseHolder {

        @BindView(R.id.jc_player)
        JCVideoPlayerStandard JCPlayer;

        VideoHolder(View view) {
            super(view);
        }
    }

    static class HtmlHolder extends BaseHolder {

        @BindView(R.id.iv_html)
        ImageView ivHtml;

        HtmlHolder(View view) {
            super(view);
        }
    }


    static class GifHolder extends BaseHolder {

        @BindView(R.id.iv_gif)
        ImageView ivGif;

        GifHolder(View view) {
            super(view);
        }
    }


    static class ImageHolder extends BaseHolder {

        @BindView(R.id.iv_image)
        ImageView ivImage;

        ImageHolder(View view) {
            super(view);
        }
    }
}
