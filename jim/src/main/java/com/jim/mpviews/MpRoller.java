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


public class MpRoller extends RelativeLayout {

    private TextView mpCounter;
    private ImageView mpTop, mpBottom;
    private int velocity = 50;
    private float x1, x2, y1, y2, constX, constY;
    int counter = 0;
    private VibratorManager vibratorManager;

    public MpRoller(Context context) {
        super(context);
        init(context);
    }

    public MpRoller(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MpRoller(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public MpRoller(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    Context context;

    public void init(Context context) {
        this.context = context;
        vibratorManager = new VibratorManager(getContext());
        LayoutInflater.from(context).inflate(R.layout.mp_roller, this);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(layoutParams);
        mpCounter = (TextView) findViewById(R.id.mpCounter);
        mpTop = (ImageView) findViewById(R.id.mpTop);
        mpBottom = (ImageView) findViewById(R.id.mpBottom);
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
                int viewWidth = getWidth();
                x2 = ev.getX();
                y2 = ev.getY();
                if (x2 < x1 + viewWidth/2 && x2 > x1 - viewWidth/2) {
                    if (y1 + velocity < y2 || y1 > velocity + y2) {
                        if (y1 < y2) {
                            if (counter > 0) {
                                counter--;
                            } else {
                                counter = 0;
                            }
                            y1 = y2;
                            mpCounter.setText("" + counter);
                            vibratorManager.startVibrate();
                            return true;
                        } else if (y1 > y2) {
                            counter++;
                            y1 = y2;
                            mpCounter.setText("" + counter);
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
                float y3 = ev.getY();
                if (Math.abs(constY - y3) < velocity)
                {
                    if (constY < getHeight()/2)
                    {
                        counter++;
                        mpCounter.setText("" + counter);
                        vibratorManager.startVibrate();
                    }
                    if (constY > getHeight()/2)
                    {
                        if (counter > 0) {
                            counter--;
                        } else {
                            counter = 0;
                        }
                        mpCounter.setText("" + counter);
                        vibratorManager.startVibrate();
                    }
                }
                break;
            }
        }

        return super.onInterceptTouchEvent(ev);
    }

    public String getUnit(){
        return mpCounter.getText().toString();
    }
}
