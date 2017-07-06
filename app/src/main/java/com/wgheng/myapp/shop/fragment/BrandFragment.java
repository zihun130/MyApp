package com.wgheng.myapp.shop.fragment;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseFragment;
import com.wgheng.myapp.common.Constant;
import com.wgheng.myapp.shop.adapter.BrandRecyclerAdapter;

import butterknife.BindView;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

/**
 * Created by wgheng on 2017/7/5.
 */

public class BrandFragment extends BaseFragment {

    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.refresh)
    PtrClassicFrameLayout refresh;
    private BrandRecyclerAdapter adapter;

    @Override
    protected View initView() {
        return View.inflate(getActivity(), R.layout.fragment_shop_child, null);
    }

    @Override
    protected void initData() {
        super.initData();
        refresh.setPtrHandler(new RefreshListener());
    }

    @Override
    protected String getUrl() {
        return Constant.BRAND_URL;
    }

    @Override
    protected void processData(String json) {
        Log.d("brand", "processData: " + json);

    }

    private class RefreshListener implements PtrHandler {
        @Override
        public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
            return true;
        }

        @Override
        public void onRefreshBegin(PtrFrameLayout frame) {

        }
    }
}
