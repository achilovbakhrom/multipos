package com.jim.mpviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jim.mpviews.utils.VibratorManager;

import java.util.ArrayList;


public class MpCounter extends RelativeLayout {

    private TextView mpCounter;
    private ImageView mpLeft, mpRight;
    private int velocity = 50;
    private float x1, x2, y1, y2, constX, constY;
    int counter = 0, max_value = 0;
    ArrayList<String> arrayList;
    private VibratorManager vibratorManager;

    public MpCounter(Context context) {
        super(context);
        init(context);
    }

    public MpCounter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MpCounter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public MpCounter(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    Context context;

    public void init(Context context) {
        this.context = context;
        vibratorManager = new VibratorManager(getContext());
        LayoutInflater.from(context).inflate(R.layout.mp_counter, this);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(layoutParams);
        mpCounter = (TextView) findViewById(R.id.mpQuantity);
        mpLeft = (ImageView) findViewById(R.id.mpLeftArrow);
        mpRight = (ImageView) findViewById(R.id.mpRightArrow);
        arrayList = new ArrayList<>();
        mpCounter.setText("" + 0);
    }

    public void setItems(ArrayList<String> items) {
        arrayList = items;
        mpCounter.setText(arrayList.get(0));
        max_value = arrayList.size();
    }

    public void setItems(String[] items) {
        for (String s : items)
            arrayList.add(s);
        mpCounter.setText(arrayList.get(0));
        max_value = arrayList.size();
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                constX = x1 = ev.getX();
                constY = y1 = ev.getY();
                return true;
            }
            case MotionEvent.ACTION_MOVE: {
                int viewHeight = getHeight();
                x2 = ev.getX();
                y2 = ev.getY();
                if (y2 < y1 + viewHeight / 2 && y2 > y1 - viewHeight / 2) {
                    if (x1 + velocity < x2 || x1 > velocity + x2) {
                        if (x1 < x2) {
                            if (max_value != 0) {
                                if (counter < max_value - 1) {
                                    counter++;
                                } else {
                                    counter = max_value - 1;
                                }
                            } else {
                                counter++;
                            }
                            x1 = x2;
                            if (!arrayList.isEmpty())
                                mpCounter.setText(arrayList.get(counter));
                            else mpCounter.setText("" + counter);
                            vibratorManager.startVibrate();
                            return true;

                        } else if (x1 > x2) {
                            if (counter > 0) {
                                counter--;
                            } else {
                                counter = 0;
                            }
                            x1 = x2;
                            if (!arrayList.isEmpty())
                                mpCounter.setText(arrayList.get(counter));
                            else mpCounter.setText("" + counter);
                            vibratorManager.startVibrate();
                            return true;
                        }
                    } else {
                        return false;
                    }
                }
                return true;
            }
            case MotionEvent.ACTION_UP: {
                float x3 = ev.getX();
                if (Math.abs(constX - x3) < velocity) {
                    if (constX > getWidth() / 2) {
                        if (max_value != 0) {
                            if (counter < max_value - 1) {
                                counter++;
                            } else {
                                counter = max_value - 1;
                            }
                        } else {
                            counter++;
                        }
                        if (!arrayList.isEmpty())
                            mpCounter.setText(arrayList.get(counter));
                        else mpCounter.setText("" + counter);
                        vibratorManager.startVibrate();
                    }
                    if (constX < getWidth() / 2) {
                        if (counter > 0) {
                            counter--;
                        } else {
                            counter = 0;
                        }
                        if (!arrayList.isEmpty())
                            mpCounter.setText(arrayList.get(counter));
                        else mpCounter.setText("" + counter);
                        vibratorManager.startVibrate();
                    }
                }
                break;
            }
        }

        return super.onInterceptTouchEvent(ev);
    }

    public String getUnit() {
        return mpCounter.getText().toString();
    }
}
