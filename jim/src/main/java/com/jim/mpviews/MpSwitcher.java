package com.jim.mpviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jim.mpviews.utils.VibratorManager;

/**
 * Created by DEV on 05.07.2017.
 */

public class MpSwitcher extends LinearLayout {

    private LinearLayout mpLeftBtn, mpRightBtn;
    private ImageView mpLeftImage, mpRightImage;
    private TextView mpLeftText, mpRightText;
    private boolean right = false;
    private boolean left = false;
    private VibratorManager vibratorManager;

    public MpSwitcher(Context context) {
        super(context);
        init(context, null);
    }

    public MpSwitcher(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MpSwitcher(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public MpSwitcher(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.mp_switch_btn, this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(layoutParams);
        mpLeftBtn = (LinearLayout) findViewById(R.id.mpLeftSide);
        mpRightBtn = (LinearLayout) findViewById(R.id.mpRightSide);
        mpLeftImage = (ImageView) findViewById(R.id.mpLeftImage);
        mpRightImage = (ImageView) findViewById(R.id.mpRightImage);
        mpLeftText = (TextView) findViewById(R.id.mpLeftText);
        mpRightText = (TextView) findViewById(R.id.mpRightText);
        TypedArray attributeArray = context.obtainStyledAttributes(attrs, R.styleable.MpSwitcher);
        vibratorManager = new VibratorManager(getContext());

        mpLeftBtn.setBackgroundResource(R.drawable.matrix_left_pressed_bg);
        mpLeftImage.setImageResource(R.drawable.ellipse);
        mpRightBtn.setBackgroundResource(R.drawable.matrix_right_bg);
        mpRightImage.setImageResource(R.drawable.ellipse_not_active);
        mpLeftText.setText(attributeArray.getText(R.styleable.MpSwitcher_text_left));
        mpRightText.setText(attributeArray.getText(R.styleable.MpSwitcher_text_right));

        mpLeftBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                vibratorManager.startVibrate();
                mpLeftBtn.setBackgroundResource(R.drawable.matrix_left_pressed_bg);
                mpLeftImage.setImageResource(R.drawable.ellipse);
                mpRightBtn.setBackgroundResource(R.drawable.matrix_right_bg);
                mpRightImage.setImageResource(R.drawable.ellipse_not_active);
                right = false;
                left = true;
            }
        });

        mpRightBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                vibratorManager.startVibrate();
                mpLeftBtn.setBackgroundResource(R.drawable.matrix_left_bg);
                mpLeftImage.setImageResource(R.drawable.ellipse_not_active);
                mpRightBtn.setBackgroundResource(R.drawable.matrix_right_pressed_bg);
                mpRightImage.setImageResource(R.drawable.ellipse);
                right = true;
                left = false;
            }
        });

        attributeArray.recycle();
    }

    public boolean isRight() {
        return right;
    }

    public boolean isLeft() {
        return left;
    }

    public void setText(String left, String right) {
        mpLeftText.setText(left);
        mpRightText.setText(right);
    }
}
