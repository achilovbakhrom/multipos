package com.jim.mpviews;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.jim.mpviews.utils.StateSaver;
import com.jim.mpviews.utils.Utils;
import com.jim.mpviews.utils.VibratorManager;

/**
 * Created by Пользователь on 24.05.2017.
 */

public class MpItem extends TextView {

    private boolean isPressed = false;
    private VibratorManager vibratorManager;

    public MpItem(Context context) {
        super(context);
        init(context, null);
    }

    public MpItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MpItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MpItem(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        vibratorManager = new VibratorManager(getContext());
        setBackgroundResource(R.drawable.item_bg);
        setPadding((int) Utils.convertDpToPixel(10), (int) Utils.convertDpToPixel(10), (int) Utils.convertDpToPixel(10), (int) Utils.convertDpToPixel(10));
        setGravity(Gravity.CENTER);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction())
                {
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
                    setBackgroundResource(R.drawable.item_pressed_bg);
                    isPressed = true;
                    setTextColor(getResources().getColor(R.color.colorBlue));
                } else {
                    setBackgroundResource(R.drawable.item_bg);
                    isPressed = false;
                    setTextColor(getResources().getColor(R.color.colorTextMain));
                }
            }
        });
    }

    private String key = null;
    public void setState(String key) {
        boolean state = StateSaver.getInstance(getContext()).getStateSaver().getBoolean(key, false);
        this.key = key;
        if (state) {
            setBackgroundResource(R.drawable.item_pressed_bg);
            isPressed = true;
        } else {
            setBackgroundResource(R.drawable.item_bg);
            isPressed = false;
        }
    }
}
