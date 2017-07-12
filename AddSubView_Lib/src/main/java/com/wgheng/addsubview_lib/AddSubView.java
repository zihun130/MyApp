package com.wgheng.addsubview_lib;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.TintTypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by wgheng on 2017/6/14.
 */

public class AddSubView extends LinearLayout implements View.OnClickListener {

    private final Context mContext;
    private ImageView ivAdd;
    private ImageView ivSub;
    private TextView tvValue;
    private int value = 0;
    private int minValue = 0;
    private int maxValue = 10;

    public AddSubView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        View view = View.inflate(context, R.layout.add_sub_view, this);
        ivAdd = (ImageView) view.findViewById(R.id.iv_add);
        ivSub = (ImageView) view.findViewById(R.id.iv_sub);
        tvValue = (TextView) view.findViewById(R.id.tv_value);

        ivAdd.setOnClickListener(this);
        ivSub.setOnClickListener(this);
        tvValue.setOnClickListener(this);

        if (attrs != null) {
            TintTypedArray tintTypedArray = TintTypedArray.obtainStyledAttributes(context, attrs, R.styleable.AddSubView);

            int value = tintTypedArray.getInt(R.styleable.AddSubView_value, 0);
            if (value > 0) {
                setValue(value);
            }

            int minValue = tintTypedArray.getInt(R.styleable.AddSubView_min_value, 0);
            if (minValue > 0) {
                setMinValue(minValue);
            }

            int maxValue = tintTypedArray.getInt(R.styleable.AddSubView_max_value, 0);
            if (maxValue > 0) {
                setMaxValue(maxValue);
            }

            Drawable drawableAdd = tintTypedArray.getDrawable(R.styleable.AddSubView_add_background);
            if(drawableAdd!=null) {
                ivAdd.setImageDrawable(drawableAdd);
            }

            Drawable drawableSub = tintTypedArray.getDrawable(R.styleable.AddSubView_sub_background);
            if(drawableSub!=null) {
                ivSub.setImageDrawable(drawableSub);
            }

        }

        tvValue.setText(value + "");

    }

    @Override
    public void onClick(View v) {
        if (v == ivAdd) {
            if (value < maxValue) {
                value++;
                tvValue.setText(value + "");
            }
        } else if (v == ivSub) {
            if (value > minValue) {
                value--;
                tvValue.setText(value + "");
            }
        }
        if (onNumberChangedListener != null) {
            onNumberChangedListener.onOnNumberChanged(value);
        }

    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
        tvValue.setText(value+"");
    }

    public int getMinValue() {
        return minValue;
    }

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    private OnNumberChangedListener onNumberChangedListener;

    public interface OnNumberChangedListener {
        void onOnNumberChanged(int value);
    }

    public void setOnNumberChangedListener(OnNumberChangedListener onNumberChangedListener) {
        this.onNumberChangedListener = onNumberChangedListener;
    }

}
