package com.wgheng.myapp.common;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseActivity;
import com.wgheng.myapp.dazen.fragment.DaRenFragment;
import com.wgheng.myapp.mgz.fragment.MGZFragment;
import com.wgheng.myapp.self.SelfFragment;
import com.wgheng.myapp.share.ShareFragment;
import com.wgheng.myapp.shop.fragment.ShopFragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.fl_main)
    FrameLayout flMain;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;
    public List<Fragment> fragments;
    private int position;
    private Fragment tempFragment;
    private boolean isExit = false;

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
                if (fragments.size() - 1 >= 5) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    if (position != 0) {
                        ft.hide(fragments.get(5)).commit();
                    } else {
                        ft.show(fragments.get(5)).commit();
                    }
                }
            }
        });
    }

    private void switchFragment(Fragment fragment) {
        if (tempFragment != fragment) {

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            if (fragment.isAdded()) {
                ft.show(fragment);
            } else {
                ft.add(R.id.fl_main, fragment,"fragment"+position);
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

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {

        //从回退栈结束当前fragment，若回退栈有fragment返回true，否则返回false
        boolean isPopped = getSupportFragmentManager().popBackStackImmediate();


        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (isPopped) {
                fragments.remove(5);
                return true;
            }

            if (!isExit) {
                isExit = true;
                new Timer().schedule(new TimerTask() {
                    @Override
                    public void run() {
                        isExit = false;
                    }
                }, 2000);
                Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
                return false;
            }
            finish();
            return true;
        }

        return super.onKeyUp(keyCode, event);
    }
}
