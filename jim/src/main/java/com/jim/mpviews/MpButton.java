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

import static com.jim.mpviews.utils.Utils.convertDpToPixel;

/**
 * Created by Пользователь on 24.05.2017.
 */

public class MpButton extends TextView {

    private boolean isPressed = false;
    private VibratorManager vibratorManager;

    public MpButton(Context context) {
        super(context);
        init(context, null);
    }

    public MpButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MpButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MpButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        vibratorManager = new VibratorManager(getContext());
        setBackgroundResource(R.drawable.button_bg);
        setPadding((int) Utils.convertDpToPixel(10), (int) Utils.convertDpToPixel(10), (int) Utils.convertDpToPixel(10), (int) Utils.convertDpToPixel(10));
        setGravity(Gravity.CENTER);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                vibratorManager.startVibrate();
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        setBackgroundResource(R.drawable.pressed_btn);
                        setTextSize(convertDpToPixel(17));
                        return false;
                    case MotionEvent.ACTION_UP:
                        setBackgroundResource(R.drawable.button_bg);
                        setTextSize(convertDpToPixel(18));
                        return false;
                }
                return false;
            }
        });
    }

    private String key = null;

    public void setState(String key) {
        boolean state = StateSaver.getInstance(getContext()).getStateSaver().getBoolean(key, false);
        this.key = key;
        if (state) {
            setBackgroundResource(R.drawable.pressed_btn);
            isPressed = true;
        } else {
            setBackgroundResource(R.drawable.button_bg);
            isPressed = false;
        }
    }
}
