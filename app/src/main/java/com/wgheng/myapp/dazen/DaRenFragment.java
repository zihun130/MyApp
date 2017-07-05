package com.wgheng.myapp.dazen;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.wgheng.myapp.base.BaseFragment;

/**
 * Created by wgheng on 2017/7/5.
 */

public class DaRenFragment extends BaseFragment {

    @Override
    protected View initView() {
        TextView textView = new TextView(getActivity());
        textView.setTextSize(30);
        textView.setGravity(Gravity.CENTER);
        textView.setText("达人");
        return textView;
    }
}
