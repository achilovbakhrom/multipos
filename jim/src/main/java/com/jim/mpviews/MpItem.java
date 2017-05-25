package com.jim.mpviews;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.jim.mpviews.utils.StateSaver;
import com.jim.mpviews.utils.Utils;

/**
 * Created by Пользователь on 24.05.2017.
 */

public class MpItem extends TextView {

    private boolean isPressed = false;

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
//        setBackgroundResource(R.drawable.item_bg);
        setPadding((int) Utils.convertDpToPixel(10), (int) Utils.convertDpToPixel(10), (int) Utils.convertDpToPixel(10), (int) Utils.convertDpToPixel(10));
        setGravity(Gravity.CENTER);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPressed) {
                    setBackgroundResource(R.drawable.pressed_btn);
                    isPressed = true;
                    setTextColor(getResources().getColor(R.color.colorBlue));
                } else {
                    setBackgroundResource(R.drawable.button_bg);
                    isPressed = false;
                    setTextColor(getResources().getColor(R.color.colorTextMain));
                }
            }
        });
    }

    public void setBackground(int res) {
        setBackgroundResource(res);
    }

    public void setText(String text) {
        setText(text);
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
