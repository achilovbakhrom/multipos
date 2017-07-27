package com.jim.mpviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jim.mpviews.utils.StateSaver;

/**
 * Created by Пользователь on 23.05.2017.
 */

public class MpCheckbox extends RelativeLayout {

    CheckBox checkBox;
    TextView textView;

    public MpCheckbox(Context context) {
        super(context);
        init(context, null);
    }

    public MpCheckbox(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }


    public MpCheckbox(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MpCheckbox(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }


    public void init(final Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.mp_checkbox, this);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(layoutParams);
        TypedArray attributeArray = context.obtainStyledAttributes(attrs, R.styleable.MpCheckbox);
        checkBox = (CheckBox) findViewById(R.id.mpCheckbox);
        textView = (TextView) findViewById(R.id.tvCheckbox);
        boolean state = attributeArray.getBoolean(R.styleable.MpCheckbox_checked, false);
        boolean without_text = attributeArray.getBoolean(R.styleable.MpCheckbox_without_text, false);
        boolean enabled = attributeArray.getBoolean(R.styleable.MpCheckbox_without_text, true);

        String text = attributeArray.getString(R.styleable.MpCheckbox_text);
        setText(text);

        checkBox.setEnabled(enabled);
        textView.setEnabled(enabled);
        checkBox.setClickable(enabled);

        if (without_text) {
            textView.setVisibility(GONE);
        } else {
            textView.setVisibility(VISIBLE);
        }

        if (state) {
            checkBox.setBackgroundResource(R.drawable.checked);
            checkBox.setChecked(true);
        } else {
            checkBox.setBackgroundResource(R.drawable.unchecked);
            checkBox.setChecked(false);
        }

        checkBox.setButtonDrawable(null);

        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    checkBox.setBackgroundResource(R.drawable.unchecked);
                    checkBox.setChecked(false);
                    animateCheckbox();
                } else {
                    checkBox.setBackgroundResource(R.drawable.checked);
                    checkBox.setChecked(true);
                    animateCheckbox();
                }
            }
        });
        attributeArray.recycle();
    }

    private String key = null;

    public void setState(String key) {
        boolean state = StateSaver.getInstance(getContext()).getStateSaver().getBoolean(key, false);
        this.key = key;
        if (state) {
            checkBox.setBackgroundResource(R.drawable.checked);
            checkBox.setChecked(true);
        } else {
            checkBox.setBackgroundResource(R.drawable.checked);
            checkBox.setChecked(false);
        }
    }

    public void animateCheckbox() {
        Animation anim = new ScaleAnimation(
                0.5f, 1f,
                0.5f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setFillAfter(true);
        anim.setDuration(200);
        checkBox.startAnimation(anim);
    }

    public void setText(String text) {
        textView.setText(text);
    }

    public void setChecked(Boolean state) {
        if (state) {
            checkBox.setBackgroundResource(R.drawable.checked);
            checkBox.setChecked(true);
        } else {
            checkBox.setBackgroundResource(R.drawable.unchecked);
            checkBox.setChecked(false);
        }
    }

    public boolean isChecked() {
        return checkBox.isChecked();
    }

    public void setEnabled(boolean state) {
        checkBox.setEnabled(state);
    }

    public void setClickable(boolean clickable) {checkBox.setClickable(clickable);}
}
