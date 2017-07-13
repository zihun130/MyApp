package com.wgheng.myapp.share.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseFragment;
import com.wgheng.myapp.share.adapter.SharePagerAdapter;
import com.wgheng.myapp.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by wgheng on 2017/7/5.
 */

public class ShareFragment extends BaseFragment {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.vp)
    ViewPager vp;

    @Override
    protected View initView() {
        return View.inflate(getActivity(), R.layout.fragment_share, null);
    }

    @Override
    protected void initData() {
        super.initData();
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new RdFragment());
        fragments.add(new JokeFragment());
        SharePagerAdapter adapter = new SharePagerAdapter(getChildFragmentManager(),fragments);
        vp.setAdapter(adapter);
        tabLayout.setupWithViewPager(vp);

        UIUtils.setIndicator(getActivity(),tabLayout,80,80);
    }
}
