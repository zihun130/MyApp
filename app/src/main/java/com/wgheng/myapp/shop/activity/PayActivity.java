package com.wgheng.myapp.shop.activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.chaychan.library.ExpandableLinearLayout;
import com.wgheng.addsubview_lib.AddSubView;
import com.wgheng.myapp.R;
import com.wgheng.myapp.base.BaseActivity;
import com.wgheng.myapp.common.CartDataHelper;
import com.wgheng.myapp.shop.bean.CartBean;
import com.wgheng.myapp.utils.DensityUtil;
import com.wgheng.myapp.utils.pay.AuthResult;
import com.wgheng.myapp.utils.pay.OrderInfoUtil2_0;
import com.wgheng.myapp.utils.pay.PayResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.wgheng.myapp.utils.pay.PayKeys.APPID;
import static com.wgheng.myapp.utils.pay.PayKeys.RSA2_PRIVATE;
import static com.wgheng.myapp.utils.pay.PayKeys.RSA_PRIVATE;

public class PayActivity extends BaseActivity {

    @BindView(R.id.ell_goods)
    ExpandableLinearLayout ellGoods;
    @BindView(R.id.cb_ali_pay)
    CheckBox cbAliPay;
    @BindView(R.id.cb_wechat_pay)
    CheckBox cbWechatPay;
    @BindView(R.id.ell_pay_type)
    ExpandableLinearLayout ellPayType;
    @BindView(R.id.tv_total_money)
    TextView tvTotalMoney;
    @BindView(R.id.tv_saving_money)
    TextView tvSavingMoney;
    @BindView(R.id.tv_pay)
    TextView tvPay;
    @BindView(R.id.tv_total_price)
    TextView tvTotalPrice;
    private ArrayList<CartBean> goods;
    private double price;

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(PayActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(PayActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        Toast.makeText(PayActivity.this,
                                "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT)
                                .show();
                    } else {
                        // 其他状态值则为授权失败
                        Toast.makeText(PayActivity.this,
                                "授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();

                    }
                    break;
                }
                default:
                    break;
            }
        };
    };

    /**
     * 支付宝支付业务
     *
     */
    public void aliPay() {
        if (TextUtils.isEmpty(APPID) || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))) {
            new AlertDialog.Builder(this).setTitle("警告").setMessage("需要配置APPID | RSA_PRIVATE")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                            //
                            finish();
                        }
                    }).show();
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * orderInfo的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> params = OrderInfoUtil2_0.buildOrderParamMap(APPID, rsa2);
        String orderParam = OrderInfoUtil2_0.buildOrderParam(params);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(params, privateKey, rsa2);
        final String orderInfo = orderParam + "&" + sign;

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(PayActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_pay;
    }

    @Override
    protected void initData() {
        super.initData();
        goods = (ArrayList) getIntent().getSerializableExtra("checked_goods");

        showGoodsList();

        computeTotalPrice();

        cbAliPay.setChecked(true);

    }

    public void computeTotalPrice() {
        List<CartBean> cartBeans = CartDataHelper.getInstance().getCartBeans();
        price = 0;
        double originPrice = 0;

        for (int i = 0; i < cartBeans.size(); i++) {
            CartBean cartBean = cartBeans.get(i);
            if (cartBean.isChecked()) {
                //计算总价
                price += Double.parseDouble(cartBean.getPrice()) * cartBean.getCount();
                originPrice += Double.parseDouble(cartBean.getOriginPrice()) * cartBean.getCount();
            }
        }
        //设置视图数据
        updateTotalPrice(price, originPrice - price);
    }

    public void updateTotalPrice(double price, double discount) {
        tvTotalPrice.setText("总计:￥" + String.format("%.2f", price));
        tvTotalMoney.setText("总计:￥" + String.format("%.2f", price));
        tvSavingMoney.setText("-￥" + String.format("%.2f", discount));
        tvSavingMoney.setText("已节省:￥" + String.format("%.2f", discount));

        if (discount != 0) {
            tvSavingMoney.setVisibility(View.VISIBLE);
        } else {
            tvSavingMoney.setVisibility(View.GONE);
        }
    }

    private void showGoodsList() {
        for (int i = 0; i < goods.size(); i++) {
            View view = View.inflate(this, R.layout.item_cart, null);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, DensityUtil.dip2px(this, 80));
            params.bottomMargin = DensityUtil.dip2px(this, 3);
            params.topMargin = DensityUtil.dip2px(this, 3);
            view.setLayoutParams(params);

            ellGoods.addView(view);

            ViewHolder holder = new ViewHolder(view);
            CartBean cartBean = goods.get(i);
            Glide.with(this).load(cartBean.getImagePath()).into(holder.ivGoods);
            holder.tvGoodsName.setText(cartBean.getGoodsName());
            holder.tvRealPrice.setText("￥" + cartBean.getPrice());

            if (cartBean.getPrice().equals(cartBean.getOriginPrice())) {
                holder.tvOriginPrice.setVisibility(View.GONE);
            } else {
                holder.tvOriginPrice.setVisibility(View.VISIBLE);
                holder.tvOriginPrice.setText("￥" + cartBean.getOriginPrice());
                holder.tvOriginPrice.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG);
            }

            holder.tvCount.setText("×" + cartBean.getCount());
            holder.cbItem.setVisibility(View.GONE);

            //设置款式信息（拼接字符串）
            holder.tvGoodsType.setText(cartBean.getTypes());

        }
    }


    @OnClick({R.id.cb_ali_pay, R.id.cb_wechat_pay,R.id.tv_pay})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cb_ali_pay:
                cbWechatPay.setChecked(false);
                break;
            case R.id.cb_wechat_pay:
                cbAliPay.setChecked(false);
                break;
            case R.id.tv_pay:
                if (cbAliPay.isChecked()) {
                    aliPay();
                } else if(cbWechatPay.isChecked()) {
                    Toast.makeText(PayActivity.this, "微信支付不可用", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    static class ViewHolder {
        @BindView(R.id.cb_item)
        CheckBox cbItem;
        @BindView(R.id.iv_goods)
        ImageView ivGoods;
        @BindView(R.id.add_sub_view)
        AddSubView addSubView;
        @BindView(R.id.tv_goods_name)
        TextView tvGoodsName;
        @BindView(R.id.tv_goods_type)
        TextView tvGoodsType;
        @BindView(R.id.tv_real_price)
        TextView tvRealPrice;
        @BindView(R.id.tv_origin_price)
        TextView tvOriginPrice;
        @BindView(R.id.tv_count)
        TextView tvCount;
        @BindView(R.id.tv_delete)
        TextView tvDelete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
