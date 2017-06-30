package com.jim.mpviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jim.mpviews.utils.VibratorManager;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Пользователь on 22.06.2017.
 */

public class MpHorizontalScroller extends LinearLayout implements View.OnClickListener {

    private TextView mpCenterText, mpCounter;
    private LinearLayout mpCenter;
    private VibratorManager vibratorManager;
    private ArrayList<String> arrayList;
    int counter = 0;

    public MpHorizontalScroller(Context context) {
        super(context);
        init(context);
    }

    public MpHorizontalScroller(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MpHorizontalScroller(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public MpHorizontalScroller(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        vibratorManager = new VibratorManager(getContext());
        LayoutInflater.from(context).inflate(R.layout.mp_horizontal_scroller, this);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(layoutParams);
        arrayList = new ArrayList<>();
        mpCenterText = (TextView) findViewById(R.id.mpCenterText);
        mpCounter = (TextView) findViewById(R.id.mpCounter);
        mpCounter.setText("0000");
        mpCenter = (LinearLayout) findViewById(R.id.mpCenter);
    }

    public void setItems(ArrayList<String> items) {
        arrayList = items;
        mpCounter.setText(arrayList.get(0));
    }

    public void setItems(String[] items) {
        arrayList.addAll(Arrays.asList(items));
        mpCounter.setText(arrayList.get(0));
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.mpLeftArrow) {
            if (counter > 0) {
                counter--;
            } else {
                counter = 0;
            }
            if (!arrayList.isEmpty())
                mpCounter.setText(arrayList.get(counter));

            vibratorManager.startVibrate();
        } else if (id == R.id.mpRightArrow) {
            if (counter < arrayList.size() - 1) {
                counter++;
            } else {
                counter = arrayList.size() - 1;
            }
            if (!arrayList.isEmpty())
                mpCounter.setText(arrayList.get(counter));

            vibratorManager.startVibrate();
        }
    }

    public void setOnItemClickListener(OnClickListener onItemClickListener) {
        mpCenter.setOnClickListener(onItemClickListener);
    }
}
