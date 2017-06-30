package com.jim.mpviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jim.mpviews.R;
import com.jim.mpviews.utils.StateSaver;
import com.jim.mpviews.utils.Utils;
import com.jim.mpviews.utils.VibratorManager;

/**
 * Created by Пользователь on 24.05.2017.
 */

public class MpRightButton extends RelativeLayout {

    private boolean isPressed = false;
    private VibratorManager vibratorManager;
    private ImageView imageView;
    private TextView textView;

    public MpRightButton(Context context) {
        super(context);
        init(context, null);
    }

    public MpRightButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MpRightButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MpRightButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.mp_right_btn, this);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(layoutParams);

        TypedArray attributeArray = context.obtainStyledAttributes(attrs, R.styleable.MpRightButton);
        String text = attributeArray.getString(R.styleable.MpRightButton_text_right);
        isPressed = attributeArray.getBoolean(R.styleable.MpRightButton_pressed, false);
        vibratorManager = new VibratorManager(getContext());
        imageView = (ImageView) findViewById(R.id.mpBtnCircle);
        textView = (TextView) findViewById(R.id.mpBtnText);
        if (!isPressed) {
            setBackgroundResource(R.drawable.matrix_right_bg);
            imageView.setImageResource(R.drawable.ellipse_not_active);
            isPressed = false;
        } else {
            setBackgroundResource(R.drawable.matrix_right_pressed_bg);
            imageView.setImageResource(R.drawable.ellipse);
            isPressed = true;
        }
        textView.setText(text);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        vibratorManager.startVibrate();
                        if (!isPressed) {
                            setBackgroundResource(R.drawable.matrix_right_pressed_bg);
                            imageView.setImageResource(R.drawable.ellipse);
                            isPressed = true;
                        } else {
                            setBackgroundResource(R.drawable.matrix_right_bg);
                            imageView.setImageResource(R.drawable.ellipse_not_active);
                            isPressed = false;
                        }
                        break;
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
            setBackgroundResource(R.drawable.matrix_right_pressed_bg);
            imageView.setImageResource(R.drawable.ellipse);
            isPressed = true;
        } else {
            setBackgroundResource(R.drawable.matrix_right_bg);
            imageView.setImageResource(R.drawable.ellipse_not_active);
            isPressed = false;
        }
    }
}
