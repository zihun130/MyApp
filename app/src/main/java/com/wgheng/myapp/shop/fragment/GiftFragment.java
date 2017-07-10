package com.wgheng.myapp.shop.fragment;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v4.app.FragmentTransaction;
        import android.view.View;
        import android.widget.ImageView;
        import android.widget.LinearLayout;

        import com.wgheng.myapp.R;
        import com.wgheng.myapp.base.BaseFragment;
        import com.wgheng.myapp.common.Constant;
        import com.wgheng.myapp.common.LoginActivity;
        import com.wgheng.myapp.common.MainActivity;

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
        Bundle bundle = new Bundle();
        String url = null;
        switch (view.getId()) {
            case R.id.iv_gift_best:
                url = urls[0];
                break;
            case R.id.iv_festival:
                url = urls[1];
                break;
            case R.id.iv_love:
                url = urls[2];
                break;
            case R.id.iv_birthday:
                url = urls[3];
                break;
            case R.id.iv_friend:
                url = urls[4];
                break;
            case R.id.iv_child:
                url = urls[5];
                break;
            case R.id.iv_parent:
                url = urls[6];
                break;
            case R.id.ll_gift_remind:
                Intent intent = new Intent(GiftFragment.this.getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
        }

        if (view.getId() != R.id.ll_gift_remind) {
            bundle.putString("url", url);
            ClassifyDetailFragment classifyDetailFragment = new ClassifyDetailFragment();
            classifyDetailFragment.setArguments(bundle);
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.setCustomAnimations(R.anim.activity_in_right, R.anim.activity_out_left,R.anim.activity_in_left, R.anim.activity_out_right);
            ft.add(R.id.fl_main, classifyDetailFragment);
            ft.hide(getActivity().getSupportFragmentManager().findFragmentByTag("fragment0"));
            ft.addToBackStack(null).commit();
            ((MainActivity) getActivity()).fragments.add(classifyDetailFragment);
        }
    }
}
