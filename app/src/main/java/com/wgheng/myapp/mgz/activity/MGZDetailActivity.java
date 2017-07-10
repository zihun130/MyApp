package com.wgheng.myapp.mgz.activity;

import android.support.annotation.IdRes;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.RadioGroup;

import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseActivity;
import com.wgheng.myapp.mgz.fragment.MGZAuthorFragment;
import com.wgheng.myapp.mgz.fragment.MGZClassifyFragment;
import com.wgheng.myapp.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class MGZDetailActivity extends BaseActivity {


    @BindView(R.id.rg_mgz)
    RadioGroup rgMgz;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.vp)
    ViewPager vp;
    private List<Fragment> fragments;
    private String[] titles = {"分类", "作者"};

    @Override
    public int getLayoutId() {
        return R.layout.activity_mgzdetail;
    }

    @Override
    protected void initView() {
        fragments = new ArrayList<>();
        fragments.add(new MGZClassifyFragment());
        fragments.add(new MGZAuthorFragment());

        vp.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(vp);
        //设置指示器宽度
        UIUtils.setIndicator(this, tabLayout, 80, 80);
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        rgMgz.check(R.id.rb_classify);
                        break;
                    case 1:
                        rgMgz.check(R.id.rb_author);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        rgMgz.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_classify:
                        vp.setCurrentItem(0);
                        break;
                    case R.id.rb_author:
                        vp.setCurrentItem(1);
                        break;
                }
            }
        });


    }

    @OnClick(R.id.ll_back)
    public void onClick() {
        finishActivity();
    }

    public void finishActivity() {
        finish();
        overridePendingTransition(R.anim.activity_in_alpha_1_1,R.anim.activity_out_top);
    }


    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }
}
