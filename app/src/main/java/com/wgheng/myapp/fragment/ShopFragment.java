package com.wgheng.myapp.fragment;

import android.view.View;
import android.widget.TextView;

import com.wgheng.myapp.base.BaseFragment;

/**
 * Created by wgheng on 2017/7/5.
 */

public class ShopFragment extends BaseFragment {

    @Override
    protected View initView() {
        TextView textView = new TextView(getActivity());
        textView.setText("商店");
        return textView;
    }
}
