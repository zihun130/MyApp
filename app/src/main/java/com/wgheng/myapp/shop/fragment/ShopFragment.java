package com.wgheng.myapp.shop.fragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wgheng.myapp.R;
import com.wgheng.myapp.shop.activity.CartActivity;
import com.wgheng.myapp.shop.adapter.ShopPagerAdapter;
import com.wgheng.myapp.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wgheng on 2017/7/5.
 */

public class ShopFragment extends BaseFragment {

    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_cart)
    ImageView ivCart;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.vp)
    ViewPager vp;

    @Override
    protected View initView() {
        return View.inflate(getActivity(), R.layout.fragemt_shop, null);
    }

    @Override
    protected void initData() {
        super.initData();
        tvTitle.setText("商店");
        ivSearch.setVisibility(View.VISIBLE);
        ivCart.setVisibility(View.VISIBLE);
        initViewPager();
    }

    private void initViewPager() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new ClassifyFragment());
        fragments.add(new BrandFragment());
        fragments.add(new HomeFragment());
        fragments.add(new TopicFragment());
        fragments.add(new GiftFragment());

        FragmentManager manager = getActivity().getSupportFragmentManager();
        ShopPagerAdapter adapter = new ShopPagerAdapter(manager,fragments);
        vp.setAdapter(adapter);
        tabLayout.setupWithViewPager(vp);
        vp.setCurrentItem(2);
    }


    @OnClick({R.id.iv_search, R.id.iv_cart})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_search:
                Toast.makeText(getActivity(), "搜索", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_cart:
                startActivity(new Intent(getActivity(), CartActivity.class));
                break;
        }
    }
}
