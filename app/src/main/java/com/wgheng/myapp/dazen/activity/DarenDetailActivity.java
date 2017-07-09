package com.wgheng.myapp.dazen.activity;

import android.support.annotation.IdRes;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.bumptech.glide.Glide;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseActivity;
import com.wgheng.myapp.common.Constant;
import com.wgheng.myapp.dazen.adapter.DarenDetialRecyclerAdapter;
import com.wgheng.myapp.dazen.bean.DarenDetialBean;

import butterknife.BindView;

public class DarenDetailActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    private int page = 1;
    private String type = RECOMMEND;
    public static final String LIKE = "masterLike";
    public static final String RECOMMEND = "masterListInfo";
    public static final String MARK = "masterFollow";
    public static final String FANS = "masterFollowed";
    private View headView;
    private DarenDetialRecyclerAdapter adapter;
    private DarenDetialBean darenDetialBean;
    private boolean isFisrtLoad = true;
    private RadioGroup rgDaren;


    @Override
    public int getLayoutId() {
        return R.layout.activity_daren_detail;
    }

    @Override
    protected void initView() {
        super.initView();
        headView = View.inflate(this, R.layout.head_view_daren_deatial, null);
        recyclerView.addHeaderView(headView);
    }

    @Override
    protected String getUrl() {
        return combineUrl();
    }

    private String combineUrl() {
        String user_id = getIntent().getStringExtra("user_id");
        return Constant.DAREN_DETIAL_URL_PART1 + type + Constant.DAREN_DETIAL_URL_PART2
                + user_id + Constant.DAREN_DETIAL_URL_PART3 + page + Constant.DAREN_DETIAL_URL_PART4;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void processData(String json) {
        super.processData(json);
        Log.d("tag", "processData: daren" + json);
        darenDetialBean = JSON.parseObject(json, DarenDetialBean.class);

        if (isFisrtLoad) {
            isFisrtLoad = false;
            setHeadView(darenDetialBean);
        }

        setHeadViewListener();

        showRecyclerView(darenDetialBean);
    }

    //根据按钮RB状态切换数据
    private void setHeadViewListener() {
        rgDaren.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_daren_like:
                        type = LIKE;
                        break;
                    case R.id.rb_daren_recommend:
                        type = RECOMMEND;
                        break;
                    case R.id.rb_daren_mark:
                        type = MARK;
                        break;
                    case R.id.rb_daren_fans:
                        type = FANS;
                        break;
                }
                getData(combineUrl());
            }
        });
    }

    private void showRecyclerView(DarenDetialBean darenDetialBean) {

        //根据RB状态切换列表显示形式
        if (LIKE.equals(type) || RECOMMEND.equals(type)) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        } else if (MARK.equals(type) || FANS.equals(type)) {
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        }

        if (adapter == null) {
            adapter = new DarenDetialRecyclerAdapter(this, type, darenDetialBean.getData().getItems());
            recyclerView.setAdapter(adapter);
        } else {
            adapter.changDataType(type, darenDetialBean.getData().getItems());
        }
    }

    private void setHeadView(DarenDetialBean darenDetialBean) {

        ImageView ivUser = (ImageView) headView.findViewById(R.id.iv_user);
        TextView tvUsername = (TextView) headView.findViewById(R.id.tv_username);
        TextView tvUsernameDesc = (TextView) headView.findViewById(R.id.tv_username_desc);
        rgDaren = (RadioGroup) headView.findViewById(R.id.rg_daren);
        RadioButton rbDarenLike = (RadioButton) headView.findViewById(R.id.rb_daren_like);
        RadioButton rbDarenRecommend = (RadioButton) headView.findViewById(R.id.rb_daren_recommend);
        RadioButton rbDarenMark = (RadioButton) headView.findViewById(R.id.rb_daren_mark);
        RadioButton rbDarenFans = (RadioButton) headView.findViewById(R.id.rb_daren_fans);

        //设置headView数据
        tvUsername.setText(darenDetialBean.getData().getItems().getUser_name());
        tvUsernameDesc.setText(darenDetialBean.getData().getItems().getUser_desc());
        rbDarenLike.setText("喜欢\n" + darenDetialBean.getData().getItems().getLike_count());
        rbDarenRecommend.setText("推荐\n" + darenDetialBean.getData().getItems().getRecommendation_count());
        rbDarenMark.setText("关注\n" + darenDetialBean.getData().getItems().getFollowing_count());
        rbDarenFans.setText("粉丝\n" + darenDetialBean.getData().getItems().getFollowed_count());
        Glide.with(this).load(darenDetialBean.getData().getItems().getUser_image().getOrig()).into(ivUser);

    }

}
