package com.jim.mpviews;

import android.content.Context;
import android.content.res.ColorStateList;
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

import com.jim.mpviews.utils.StateSaver;
import com.jim.mpviews.utils.Utils;
import com.jim.mpviews.utils.VibratorManager;

/**
 * Created by Пользователь on 24.05.2017.
 */

public class MpButtonWithIcon extends RelativeLayout {

    private ImageView mpBtnIcon;
    private TextView mpBtnText;

    private boolean isPressed = false;
    private VibratorManager vibratorManager;

    public MpButtonWithIcon(Context context) {
        super(context);
        init(context, null);
    }

    public MpButtonWithIcon(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MpButtonWithIcon(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MpButtonWithIcon(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        vibratorManager = new VibratorManager(getContext());
        LayoutInflater.from(context).inflate(R.layout.mp_btn_with_icon, this);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(layoutParams);
        TypedArray attributeArray = context.obtainStyledAttributes(attrs, R.styleable.MpButtonWithIcon);

        mpBtnIcon = (ImageView) findViewById(R.id.mpBtnIcon);
        mpBtnText = (TextView) findViewById(R.id.mpBtnText);
        String text = attributeArray.getString(R.styleable.MpButtonWithIcon_btn_text);
        mpBtnText.setText(text);
        mpBtnIcon.setImageTintList(ColorStateList.valueOf(attributeArray.getColor(R.styleable.MpButtonWithIcon_tint_color, getResources().getColor(R.color.colorBlue))));
        mpBtnIcon.setImageResource(attributeArray.getResourceId(R.styleable.MpButtonWithIcon_src, 0));
        setBackgroundResource(R.drawable.button_bg);
        setPadding((int) Utils.convertDpToPixel(10), (int) Utils.convertDpToPixel(10), (int) Utils.convertDpToPixel(10), (int) Utils.convertDpToPixel(10));
        setGravity(Gravity.CENTER);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        vibratorManager.startVibrate();
                        if (!isPressed) {
                            setBackgroundResource(R.drawable.pressed_btn);
                            isPressed = true;
                        } else {
                            setBackgroundResource(R.drawable.button_bg);
                            isPressed = false;
                        }
                        break;
                }
                return false;
            }
        });
        attributeArray.recycle();
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
