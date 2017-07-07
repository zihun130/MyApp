package com.wgheng.myapp.shop.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseFragment;
import com.wgheng.myapp.common.Constant;
import com.wgheng.myapp.common.LoginActivity;
import com.wgheng.myapp.shop.activity.ClassifyDetailActivity;

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
    private String[] urls = {Constant.GIFT_URL, Constant.FESTIVAL_URL, Constant.LOVE_URL,
            Constant.BIRTHDAY_URL, Constant.FRIEND_URL, Constant.CHILD_URL, Constant.PARENT_URL};

    @Override
    protected View initView() {
        return View.inflate(getActivity(), R.layout.fragment_gift, null);
    }

    @OnClick({R.id.iv_gift_best, R.id.iv_festival, R.id.iv_love, R.id.iv_birthday, R.id.iv_friend, R.id.iv_child, R.id.iv_parent, R.id.ll_gift_remind})
    public void onClick(View view) {
        Intent intent = new Intent(GiftFragment.this.getActivity(), ClassifyDetailActivity.class);
        switch (view.getId()) {
            case R.id.iv_gift_best:
                intent.putExtra("url", urls[0]);
                break;
            case R.id.iv_festival:
                intent.putExtra("url", urls[1]);
                break;
            case R.id.iv_love:
                intent.putExtra("url", urls[2]);
                break;
            case R.id.iv_birthday:
                intent.putExtra("url", urls[3]);
                break;
            case R.id.iv_friend:
                intent.putExtra("url", urls[4]);
                break;
            case R.id.iv_child:
                intent.putExtra("url", urls[5]);
                break;
            case R.id.iv_parent:
                intent.putExtra("url", urls[6]);
                break;
            case R.id.ll_gift_remind:
                intent = new Intent(GiftFragment.this.getActivity(), LoginActivity.class);
                break;
        }
        startActivity(intent);
    }
}
