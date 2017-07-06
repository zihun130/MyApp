package com.wgheng.myapp.common;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioGroup;

import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseActivity;
import com.wgheng.myapp.dazen.fragment.DaRenFragment;
import com.wgheng.myapp.mgz.MGZFragment;
import com.wgheng.myapp.self.SelfFragment;
import com.wgheng.myapp.share.ShareFragment;
import com.wgheng.myapp.shop.fragment.ShopFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.fl_main)
    FrameLayout flMain;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;
    private List<Fragment> fragments;
    private int position;
    private Fragment tempFragment;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        super.initView();
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rb_shop:
                        position = 0;
                        break;
                    case R.id.rb_mgz:
                        position = 1;
                        break;
                    case R.id.rb_dazen:
                        position = 2;
                        break;
                    case R.id.rb_share:
                        position = 3;
                        break;
                    case R.id.rb_self:
                        position = 4;
                        break;
                }
                Fragment fragment = fragments.get(position);
                switchFragment(fragment);
            }
        });
    }

    private void switchFragment(Fragment fragment) {
        if (tempFragment != fragment) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            if (fragment.isAdded()) {
                ft.show(fragment);
            } else {
                ft.add(R.id.fl_main, fragment);
            }

            if (tempFragment != null) {
                ft.hide(tempFragment);
            }

            ft.commit();
            tempFragment = fragment;
        }
    }

    @Override
    protected void initData() {
        initFragment();
        rgMain.check(R.id.rb_shop);
    }

    private void initFragment() {
        fragments = new ArrayList<>();
        fragments.add(new ShopFragment());
        fragments.add(new MGZFragment());
        fragments.add(new DaRenFragment());
        fragments.add(new ShareFragment());
        fragments.add(new SelfFragment());
    }

}
