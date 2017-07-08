package com.wgheng.myapp.dazen.activity;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseActivity;
import com.wgheng.myapp.common.Constant;

import butterknife.BindView;

public class DarenDetailActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    private int page = 1;
    private String variable = RECOMMEND;
    public static final String LIKE = "masterLike";
    public static final String RECOMMEND = "masterListInfo";
    public static final String MARK = "masterFollow";
    public static final String FANS = "masterFollowed";


    @Override
    public int getLayoutId() {
        return R.layout.activity_daren_detail;
    }

    @Override
    protected void initView() {
        super.initView();
        View headView = View.inflate(this, R.layout.head_view_daren_deatial, null);
        recyclerView.addHeaderView(headView);
    }

    @Override
    protected String getUrl() {
        return combineUrl();
    }

    private String combineUrl() {
        String user_id = getIntent().getStringExtra("user_id");
        return Constant.DAREN_DETIAL_URL_PART1 + variable + Constant.DAREN_URL_PART2
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
        if (LIKE.equals(variable) || RECOMMEND.equals(variable)) {


        } else if(MARK.equals(variable) || FANS.equals(variable)){


        }
    }

}
