package com.wgheng.myapp.mgz.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.wgheng.myapp.base.BaseFragment;

/**
 * Created by wgheng on 2017/7/10.
 */

public class MGZClassifyFragment extends BaseFragment{
    @Override
    protected View initView() {
        TextView textView = new TextView(getActivity());
        textView.setText("分类");
        textView.setTextSize(20);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
