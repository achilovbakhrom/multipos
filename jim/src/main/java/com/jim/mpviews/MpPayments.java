package com.jim.mpviews;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jim.mpviews.utils.StateSaver;
import com.jim.mpviews.utils.VibratorManager;


/**
 * Created by Пользователь on 26.05.2017.
 */

public class MpPayments extends RelativeLayout {

    private TextView mpType, mpPercent, mpName;
    private LinearLayout mpPayment;
    private boolean isPressed = false;
    private VibratorManager vibratorManager;

    public MpPayments(Context context) {
        super(context);
        init(context, null);
    }

    public MpPayments(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MpPayments(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MpPayments(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        vibratorManager = new VibratorManager(getContext());
        LayoutInflater.from(context).inflate(R.layout.mp_payments, this);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(layoutParams);
        TypedArray attributeArray = context.obtainStyledAttributes(attrs, R.styleable.MpPayments);
        mpType = (TextView) findViewById(R.id.mpType);
        mpPercent = (TextView) findViewById(R.id.mpPercent);
        mpPayment = (LinearLayout) findViewById(R.id.mpPayment);
        mpPayment.setBackgroundResource(R.drawable.rounded_btn);
        mpType.setTextColor(getResources().getColor(R.color.colorBlue));
        mpType.setText(attributeArray.getText(R.styleable.MpPayments_type));
        mpPercent.setText("+" + attributeArray.getText(R.styleable.MpPayments_percent) + "%");
        mpPercent.setTextColor(getResources().getColor(R.color.colorSecondaryText));
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        vibratorManager.startVibrate();
                        if (!isPressed) {
                            mpPayment.setBackgroundResource(R.drawable.rounded_blue_btn);
                            mpType.setTextColor(getResources().getColor(R.color.colorWhite));
                            mpPercent.setTextColor(getResources().getColor(R.color.colorWhite));
                            isPressed = true;
                        } else {
                            mpPayment.setBackgroundResource(R.drawable.rounded_btn);
                            mpType.setTextColor(getResources().getColor(R.color.colorBlue));
                            mpPercent.setTextColor(getResources().getColor(R.color.colorSecondaryText));
                            isPressed = false;
                        }
                        break;
                }
                return false;
            }
        });
        attributeArray.recycle();
    }

    public void setType(String type) {
        mpType.setText(type);
    }

    public void setPercent(String percent) {
        mpPercent.setText("+" + percent + "%");
    }


    public void setVisibility(int type, int percent) {
        mpType.setVisibility(type);
        mpPercent.setVisibility(percent);
    }

    private String key = null;

    public void setState(String key) {
        boolean state = StateSaver.getInstance(getContext()).getStateSaver().getBoolean(key, false);
        this.key = key;
        if (state) {
            mpPayment.setBackgroundResource(R.drawable.rounded_blue_btn);
            isPressed = true;
        } else {
            mpPayment.setBackgroundResource(R.drawable.rounded_btn);
            isPressed = false;
        }
    }
}
