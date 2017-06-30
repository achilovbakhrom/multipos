package com.jim.mpviews.spinner;

import android.content.Context;
import android.media.Image;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jim.mpviews.R;

/**
 * Created by Пользователь on 21.06.2017.
 */

public class MpSpinnerMainView extends LinearLayout {

    private ImageView arrow;
    private TextView text;

    public MpSpinnerMainView(Context context) {
        super(context);
        init(context);
    }

    public MpSpinnerMainView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MpSpinnerMainView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public MpSpinnerMainView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.mp_new_spinner, this);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(layoutParams);
        setOrientation(HORIZONTAL);
        text = (TextView) findViewById(R.id.mpSpinnerText);
        arrow = (ImageView) findViewById(R.id.mpSpinnerArrow);
        text.setText("Spinner Item");
        arrow.setImageResource(R.drawable.triangle);
    }

    protected void setImageResource(int drawable) {
        arrow.setImageResource(drawable);
    }

    protected void setSpinnerText(String string) {
        text.setText(string);
    }
}
