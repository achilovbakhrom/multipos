package com.jim.mpviews;

import android.content.Context;
import android.os.Build;
import android.os.Handler;
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

public class MpNumPad extends TextView {

    private VibratorManager vibratorManager;

    public MpNumPad(Context context) {
        super(context);
        init(context);
    }

    public MpNumPad(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MpNumPad(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MpNumPad(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        vibratorManager = new VibratorManager(getContext(), 50);
        setBackgroundResource(R.drawable.num_pad_btn);
        setPadding((int) convertDpToPixel(10), (int) convertDpToPixel(10), (int) convertDpToPixel(10), (int) convertDpToPixel(10));
        setGravity(Gravity.CENTER);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                vibratorManager.startVibrate();
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        setBackgroundResource(R.drawable.item_pressed_bg);
                        setTextSize(convertDpToPixel(36));
                        return true;
                    case MotionEvent.ACTION_UP:
                        setBackgroundResource(R.drawable.num_pad_btn);
                        setTextSize(convertDpToPixel(38));
                        return true;
                }
                return false;
            }
        });
    }
}
