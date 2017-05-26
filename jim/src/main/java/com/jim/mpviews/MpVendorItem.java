package com.jim.mpviews;

import android.content.Context;
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

public class MpVendorItem extends RelativeLayout {

    private TextView mpVendor, mpItem, mpName;
    private LinearLayout llVendor;
    private boolean isPressed = false;
    private VibratorManager vibratorManager;

    public MpVendorItem(Context context) {
        super(context);
        init(context);
    }

    public MpVendorItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MpVendorItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MpVendorItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        vibratorManager = new VibratorManager(getContext());
        LayoutInflater.from(context).inflate(R.layout.mp_vendor_item, this);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(layoutParams);
        mpVendor = (TextView) findViewById(R.id.tvVendor);
        mpItem = (TextView) findViewById(R.id.tvItemQty);
        mpName = (TextView) findViewById(R.id.tvVendorName);
        llVendor = (LinearLayout) findViewById(R.id.llVendor);
        llVendor.setBackgroundResource(R.drawable.vendor_item_bg);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        vibratorManager.startVibrate();
                        break;
                }
                return false;
            }
        });

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPressed) {
                    llVendor.setBackgroundResource(R.drawable.pressed_vendor_item);
                    isPressed = true;
                } else {
                    llVendor.setBackgroundResource(R.drawable.vendor_item_bg);
                    isPressed = false;
                }
            }
        });


    }

    public void setVendor(String vendor) {
        mpVendor.setText(vendor);
    }

    public void setVendorItem(String item) {
        mpItem.setText("Item: " + item);
    }

    public void setVendorName(String name) {
        mpName.setText(name);
    }

    private String key = null;

    public void setState(String key) {
        boolean state = StateSaver.getInstance(getContext()).getStateSaver().getBoolean(key, false);
        this.key = key;
        if (state) {
            setBackgroundResource(R.drawable.pressed_vendor_item);
            isPressed = true;
        } else {
            setBackgroundResource(R.drawable.vendor_item_bg);
            isPressed = false;
        }
    }
}
