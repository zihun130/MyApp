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
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseActivity;
import com.wgheng.myapp.common.Constant;
import com.wgheng.myapp.dazen.adapter.DarenDetialRecyclerAdapter;
import com.wgheng.myapp.dazen.bean.DarenDetialBean;

import butterknife.BindView;

public class DarenDetailActivity extends BaseActivity {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    private int page = 1;
    private String type = RECOMMEND;
    public static final String LIKE = "masterLike";
    public static final String RECOMMEND = "masterListInfo";
    public static final String MARK = "masterFollow";
    public static final String FANS = "masterFollowed";
    private View headView;
    private DarenDetialRecyclerAdapter adapter;
    private DarenDetialBean darenDetialBean;
    private boolean isLoadMore = false;
    private boolean isFirstLoad = true;
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
        ivBack.setVisibility(View.VISIBLE);
        tvTitle.setVisibility(View.VISIBLE);
        tvTitle.setText("达人");
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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


        //第一次进来才加载头部数据
        if (isFirstLoad) {
            isFirstLoad = false;
            setHeadView(darenDetialBean);
        }

        if (!isLoadMore) {
            showRecyclerView(darenDetialBean);
            recyclerView.refreshComplete();
        } else {
            adapter.loadMore(darenDetialBean.getData().getItems());
            recyclerView.loadMoreComplete();
        }
        setHeadViewListener();
        setRecyclerViewListener();
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

        //第一次加载创建，之后刷新
        if (adapter == null) {
            adapter = new DarenDetialRecyclerAdapter(this, type, darenDetialBean.getData().getItems());
            recyclerView.setAdapter(adapter);
        } else {
            adapter.changDataType(type, darenDetialBean.getData().getItems());
        }

    }

    public void setRecyclerViewListener() {

        //设置刷新样式
        recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
        recyclerView.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);

        //设置下拉刷新和加载更多
        recyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                isLoadMore = false;
                page = 1;
                getData(combineUrl());
            }

            @Override
            public void onLoadMore() {
                isLoadMore = true;
                page++;
                getData(combineUrl());
            }
        });
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
