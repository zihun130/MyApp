package com.wgheng.myapp.mgz.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseActivity;
import com.wgheng.myapp.common.Constant;
import com.wgheng.myapp.mgz.adapter.MGZRecyclerAdapter;
import com.wgheng.myapp.mgz.bean.MGZBean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MGZAuthorDetailActivity extends BaseActivity {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.recycler_view)
    XRecyclerView recyclerView;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.iv_arrow)
    ImageView ivArrow;
    private MGZRecyclerAdapter adapter;
    private List<MGZBean> mgzBeans;

    @Override
    public int getLayoutId() {
        return R.layout.activity_mgzclassify;
    }

    @Override
    protected void initView() {
        super.initView();
    }

    @Override
    protected void initData() {
        super.initData();
        String title = getIntent().getStringExtra("title");
        tvTitle.setText("杂志·" + title);
        ivArrow.setVisibility(View.VISIBLE);
    }

    @Override
    protected String getUrl() {
        String author_id = getIntent().getStringExtra("author_id");
        return Constant.AUTHOR_DETIAL_URL_PART1 + author_id + Constant.AUTHOR_DETIAL_URL_PART2;
    }

    @Override
    protected void processData(String json) {
        try {
            JSONObject obj = new JSONObject(json);
            JSONObject data = obj.optJSONObject("data");
            JSONObject items = data.optJSONObject("items");
            JSONArray keys = items.optJSONArray("keys");
            JSONObject infos = items.optJSONObject("infos");

            mgzBeans = new ArrayList<>();

            for (int i = 0; i < keys.length(); i++) {
                JSONArray monthInfos = infos.optJSONArray(keys.get(i) + "");
                for (int j = 0; j < monthInfos.length(); j++) {
                    MGZBean mgzBean = new MGZBean();
                    JSONObject objMGZ = monthInfos.optJSONObject(j);
                    mgzBean.setTopic_name(objMGZ.optString("topic_name"));
                    mgzBean.setTopic_url(objMGZ.optString("topic_url"));
                    mgzBean.setCat_name(objMGZ.optString("cat_name"));
                    mgzBean.setCover_img(objMGZ.optString("cover_img"));
                    mgzBean.setMonthInfo(keys.get(i) + "");
                    mgzBeans.add(mgzBean);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        initRecyclerView(mgzBeans);
    }

    private void initRecyclerView(List<MGZBean> mgzBeans) {
        adapter = new MGZRecyclerAdapter(this, mgzBeans);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setOnItemClickListener(new MGZRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(View view, int layoutPosition) {

            }
        });
        recyclerView.setLoadingMoreEnabled(false);
        recyclerView.setPullRefreshEnabled(false);
    }


    @OnClick(R.id.iv_back)
    public void onClick() {

    }
}
