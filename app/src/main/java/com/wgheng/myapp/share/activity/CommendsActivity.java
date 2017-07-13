package com.wgheng.myapp.share.activity;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseActivity;
import com.wgheng.myapp.common.Constant;
import com.wgheng.myapp.share.bean.CommendsBean;
import com.wgheng.myapp.share.bean.RdBean;
import com.wgheng.myapp.utils.CropTopTransformation;
import com.wgheng.myapp.utils.DensityUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class CommendsActivity extends BaseActivity {

    public static final int TYPE_VIDEO = 1;
    public static final int TYPE_GIF = 2;
    public static final int TYPE_IMAGE = 3;
    public static final int TYPE_HTML = 4;
    public static final int TYPE_TEXT = 5;
    private RdBean.ListBean rdListBean;
    private LinearLayout llTopView;
    private LinearLayout llHotView;
    private LinearLayout llNewView;
    private int itemWidth;
    private BaseHolder holder;
    private int itemViewType;
    private CommendsBean commendsBean;
    private TextView tvNewCommends;
    private TextView tvHotCommends;

    @Override
    public int getLayoutId() {
        return R.layout.activity_commends;
    }

    @Override
    protected void initView() {
        super.initView();

        ImageView ivBack = (ImageView) findViewById(R.id.iv_back);
        llTopView = (LinearLayout) findViewById(R.id.ll_top_view);
        llHotView = (LinearLayout) findViewById(R.id.ll_hot_commend_container);
        llNewView = (LinearLayout) findViewById(R.id.ll_new_commend_container);
        tvNewCommends = (TextView) findViewById(R.id.tv_new_commends);
        tvHotCommends = (TextView) findViewById(R.id.tv_hot_commends);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //获取传递数据
        getIntentData();

        //初始化TopView
        initTopView();
    }


    //分类型加载上半部分布局数据
    private void initTopView() {

        //加载布局
        inflateTopView();

        //设置数据
        setTopViewData();

    }

    private void setTopViewData() {

        Glide.with(this).load(rdListBean.getU().getHeader().get(0)).into(holder.ivUserIcon);
        holder.tvUsername.setText(rdListBean.getU().getName());
        holder.tvTime.setText(rdListBean.getPasstime());
        holder.ivV.setVisibility(rdListBean.getU().isIs_v() ? View.VISIBLE : View.GONE);
        holder.ivVip.setVisibility(rdListBean.getU().isIs_vip() ? View.VISIBLE : View.GONE);
        holder.tvDing.setText(rdListBean.getUp());
        holder.tvCai.setText(rdListBean.getDown() + "");
        holder.tvShare.setText(rdListBean.getForward() + "");
        holder.tvCommend.setText(rdListBean.getComment());

        switch (itemViewType) {
            case TYPE_VIDEO:
                VideoHolder videoHolder = (VideoHolder) holder;
                videoHolder.tvTitle.setText(rdListBean.getText());
                videoHolder.JCPlayer.setUp(rdListBean.getVideo().getVideo().get(0),
                        JCVideoPlayerStandard.SCREEN_LAYOUT_NORMAL, "");
                Glide.with(this)
                        .load(rdListBean.getVideo().getThumbnail().get(0))
                        .into(videoHolder.JCPlayer.thumbImageView);
                break;
            case TYPE_GIF:
                GifHolder gifHolder = (GifHolder) holder;
                gifHolder.tvTitle.setText(rdListBean.getText());
                Glide.with(this)
                        .load(rdListBean.getGif().getImages().get(0))
                        .apply(new RequestOptions()
                                .override(DensityUtil.dip2px(this, rdListBean.getGif().getWidth()),
                                        DensityUtil.dip2px(this, rdListBean.getGif().getHeight())))
                        .into(gifHolder.ivGif);

                break;
            case TYPE_IMAGE:
                ImageHolder imageHolder = (ImageHolder) holder;
                imageHolder.tvTitle.setText(rdListBean.getText());
                Glide.with(this)
                        .load(rdListBean.getImage().getBig().get(0))
                        .apply(new RequestOptions()
                                .bitmapTransform(new CropTopTransformation(this, itemWidth, itemWidth, CropTopTransformation.CropType.TOP)))
                        .into(imageHolder.ivImage);
                break;
            case TYPE_HTML:
                HtmlHolder htmlHolder = (HtmlHolder) holder;
                htmlHolder.tvTitle.setText(rdListBean.getHtml().getTitle());
                Glide.with(this).load(rdListBean.getHtml().getThumbnail()).into(htmlHolder.ivHtml);
                break;
            case TYPE_TEXT:
                holder.tvTitle.setText(rdListBean.getText());
                break;
        }

    }

    private void inflateTopView() {

        itemViewType = getItemViewType(rdListBean);

        //获取item宽度
        itemWidth = llTopView.getWidth();
        LayoutInflater inflater = LayoutInflater.from(this);

        View view = null;
        switch (itemViewType) {
            case TYPE_VIDEO:
                view = inflater.inflate(R.layout.item_video, llTopView, false);
                holder = new VideoHolder(view);
                break;
            case TYPE_GIF:
                view = inflater.inflate(R.layout.item_gif, llTopView, false);
                holder = new GifHolder(view);
                break;
            case TYPE_IMAGE:
                view = inflater.inflate(R.layout.item_image, llTopView, false);
                holder = new ImageHolder(view);
                break;
            case TYPE_HTML:
                view = inflater.inflate(R.layout.item_html, llTopView, false);
                holder = new HtmlHolder(view);
                break;
            case TYPE_TEXT:
                view = inflater.inflate(R.layout.item_text, llTopView, false);
                holder = new BaseHolder(view);
                break;
        }

        llTopView.addView(view);

    }

    public int getItemViewType(RdBean.ListBean rdListBean) {

        String type = rdListBean.getType();
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

    //获取传递数据
    private void getIntentData() {
        Intent intent = getIntent();
        rdListBean = (RdBean.ListBean) intent.getSerializableExtra("listBean");
    }

    @Override
    protected String getUrl() {
        return combineUrl();
    }

    private String combineUrl() {
        return Constant.BS_COMMENDS_PART1 + rdListBean.getId() + Constant.BS_COMMENDS_PART2;
    }

    @Override
    protected void processData(String json) {
        super.processData(json);
        Log.d("commends", "processData: " + json);

        commendsBean = JSON.parseObject(json, CommendsBean.class);

        initHotCommends();
        initNewCommends();
    }

    private void initHotCommends() {
        List<CommendsBean.HotBean.ListBean> hotBeans = commendsBean.getHot().getList();

        if (hotBeans == null || hotBeans.size() == 0) {
            tvHotCommends.setVisibility(View.GONE);
            return;
        } else {
            tvHotCommends.setVisibility(View.VISIBLE);
        }

        llHotView.removeAllViews();

        for (int i = 0; i < hotBeans.size(); i++) {
            View itemCommend = View.inflate(this, R.layout.item_commend, null);
            llHotView.addView(itemCommend);

            CommendsHolder commendsHolder = new CommendsHolder(itemCommend);

            Glide.with(this).load(hotBeans.get(i).getUser().getProfile_image()).into(commendsHolder.ivUserIcon);
            commendsHolder.tvUsername.setText(hotBeans.get(i).getUser().getUsername());
            commendsHolder.tvCommendContent.setText(hotBeans.get(i).getContent());
            commendsHolder.tvDing.setText(hotBeans.get(i).getLike_count() + "");
            commendsHolder.tvCai.setText(hotBeans.get(i).getHate_count() + "");

            String follow = hotBeans.get(i).getUser().getTotal_cmt_like_count();
            float followCount = Float.parseFloat(follow);
            if (followCount >= 1000) {
                followCount = followCount / 1000;
                if (followCount / 10 > 1) {
                    follow = String.format("%.1f", followCount).toString();
                    commendsHolder.tvAgreeCount.setBackgroundResource(R.drawable.shape_agree_bg_super);
                } else {
                    follow = String.format("%.2f", followCount).toString();
                    commendsHolder.tvAgreeCount.setBackgroundResource(R.drawable.shape_agree_bg_more);
                }
                commendsHolder.tvAgreeCount.setText(follow + "k");
            } else {
                commendsHolder.tvAgreeCount.setText(follow);
            }

            if ("m".equals(hotBeans.get(i).getUser().getSex())) {
                commendsHolder.ivSexMan.setVisibility(View.VISIBLE);
                commendsHolder.ivSexWoman.setVisibility(View.GONE);
            } else if ("f".equals(hotBeans.get(i).getUser().getSex())) {
                commendsHolder.ivSexMan.setVisibility(View.GONE);
                commendsHolder.ivSexWoman.setVisibility(View.VISIBLE);
            }

            //设置评论图片
            CommendsBean.HotBean.ListBean.ImageBean imageBean = hotBeans.get(i).getImage();
            if (imageBean != null) {
                List<String> images = imageBean.getImages();
                if (images != null && images.size() > 0) {
                    Glide.with(this).load(images.get(0)).into(commendsHolder.ivCommendPic);
                }
            }

        }

    }


    private void initNewCommends() {
        List<CommendsBean.NormalBean.ListBeanX> normalBeans = commendsBean.getNormal().getList();

        if (normalBeans == null || normalBeans.size() == 0) {
            tvNewCommends.setVisibility(View.GONE);
            return;
        } else {
            tvNewCommends.setVisibility(View.VISIBLE);
        }

        llNewView.removeAllViews();

        for (int i = 0; i < normalBeans.size(); i++) {
            View itemCommend = View.inflate(this, R.layout.item_commend, null);
            llNewView.addView(itemCommend);

            CommendsHolder commendsHolder = new CommendsHolder(itemCommend);

            Glide.with(this).load(normalBeans.get(i).getUser().getProfile_image()).into(commendsHolder.ivUserIcon);
            commendsHolder.tvUsername.setText(normalBeans.get(i).getUser().getUsername());
            commendsHolder.tvCommendContent.setText(normalBeans.get(i).getContent());
            commendsHolder.tvCai.setText(normalBeans.get(i).getHate_count() + "");
            commendsHolder.tvDing.setText(normalBeans.get(i).getLike_count() + "");

            String follow = normalBeans.get(i).getUser().getTotal_cmt_like_count();
            float followCount = Float.parseFloat(follow);
            if (followCount >= 1000) {
                followCount = followCount / 1000;
                if (followCount / 10 > 1) {
                    follow = String.format("%.1f", followCount).toString();
                    commendsHolder.tvAgreeCount.setBackgroundResource(R.drawable.shape_agree_bg_super);
                } else {
                    follow = String.format("%.2f", followCount).toString();
                    commendsHolder.tvAgreeCount.setBackgroundResource(R.drawable.shape_agree_bg_more);
                }
                commendsHolder.tvAgreeCount.setText(follow + "k");
            } else {
                commendsHolder.tvAgreeCount.setText(follow);
            }

            //设置评论性别
            if ("m".equals(normalBeans.get(i).getUser().getSex())) {
                commendsHolder.ivSexMan.setVisibility(View.VISIBLE);
                commendsHolder.ivSexWoman.setVisibility(View.GONE);
            } else if ("f".equals(normalBeans.get(i).getUser().getSex())) {
                commendsHolder.ivSexMan.setVisibility(View.GONE);
                commendsHolder.ivSexWoman.setVisibility(View.VISIBLE);
            }

            //设置评论图片
            CommendsBean.NormalBean.ListBeanX.ImageBeanX imageBean = normalBeans.get(i).getImage();
            if (imageBean != null) {
                List<String> images = imageBean.getImages();
                if (images != null && images.size() > 0) {
                    Glide.with(this).load(images.get(0)).into(commendsHolder.ivCommendPic);
                }
            }

            //加载二级评论
            List<CommendsBean.NormalBean.ListBeanX.PrecmtsBean> precmts = normalBeans.get(i).getPrecmts();
            if (precmts != null && precmts.size() > 0) {

                commendsHolder.llSecondCommends.removeAllViews();

                for (int j = 0; j < precmts.size(); j++) {
                    View secondCommendView = View.inflate(this, R.layout.item_commend_second, null);
                    commendsHolder.llSecondCommends.addView(secondCommendView);

                    SecondCommendHolder secondCommendHolder = new SecondCommendHolder(secondCommendView);
                    CommendsBean.NormalBean.ListBeanX.PrecmtsBean precmtsBean = precmts.get(j);
                    secondCommendHolder.TvCount.setText((j+1)+"");
                    secondCommendHolder.tvUsername.setText(precmtsBean.getUser().getUsername());
                    secondCommendHolder.tvDing.setText(precmtsBean.getLike_count()+"");
                    secondCommendHolder.tvCai.setText(precmtsBean.getHate_count()+"");
                    secondCommendHolder.tvCommendContent.setText(precmtsBean.getContent());

                }
            }
        }

    }


    static class CommendsHolder {

        @BindView(R.id.iv_user_icon)
        CircleImageView ivUserIcon;
        @BindView(R.id.tv_agree_count)
        TextView tvAgreeCount;
        @BindView(R.id.iv_sex_man)
        ImageView ivSexMan;
        @BindView(R.id.iv_sex_woman)
        ImageView ivSexWoman;
        @BindView(R.id.tv_username)
        TextView tvUsername;
        @BindView(R.id.iv_ding)
        ImageView ivDing;
        @BindView(R.id.tv_ding)
        TextView tvDing;
        @BindView(R.id.iv_cai)
        ImageView ivCai;
        @BindView(R.id.tv_cai)
        TextView tvCai;
        @BindView(R.id.ll_second_commends)
        LinearLayout llSecondCommends;
        @BindView(R.id.tv_commend_content)
        TextView tvCommendContent;
        @BindView(R.id.iv_commend_pic)
        ImageView ivCommendPic;

        CommendsHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }

    static class SecondCommendHolder {
        @BindView(R.id.tv_count)
        TextView TvCount;
        @BindView(R.id.tv_username)
        TextView tvUsername;
        @BindView(R.id.iv_ding)
        ImageView ivDing;
        @BindView(R.id.tv_ding)
        TextView tvDing;
        @BindView(R.id.iv_cai)
        ImageView ivCai;
        @BindView(R.id.tv_cai)
        TextView tvCai;
        @BindView(R.id.tv_commend_content)
        TextView tvCommendContent;

        SecondCommendHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    static class BaseHolder {
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
