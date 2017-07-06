package com.wgheng.myapp.shop.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseFragment;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by wgheng on 2017/7/5.
 */

public class GiftFragment extends BaseFragment {

    @BindView(R.id.iv_gift_best)
    ImageView ivGiftBest;
    @BindView(R.id.iv_festival)
    ImageView ivFestival;
    @BindView(R.id.iv_love)
    ImageView ivLove;
    @BindView(R.id.iv_birthday)
    ImageView ivBirthday;
    @BindView(R.id.iv_friend)
    ImageView ivFriend;
    @BindView(R.id.iv_child)
    ImageView ivChild;
    @BindView(R.id.iv_parent)
    ImageView ivParent;
    @BindView(R.id.ll_gift_remind)
    LinearLayout llGiftRemind;

    @Override
    protected View initView() {
        return View.inflate(getActivity(), R.layout.fragment_gift, null);
    }

    @OnClick({R.id.iv_gift_best, R.id.iv_festival, R.id.iv_love, R.id.iv_birthday, R.id.iv_friend, R.id.iv_child, R.id.iv_parent, R.id.ll_gift_remind})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_gift_best:
                break;
            case R.id.iv_festival:
                break;
            case R.id.iv_love:
                break;
            case R.id.iv_birthday:
                break;
            case R.id.iv_friend:
                break;
            case R.id.iv_child:
                break;
            case R.id.iv_parent:
                break;
            case R.id.ll_gift_remind:
                break;
        }
    }
}
