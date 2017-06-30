package com.jim.mpviews.spinner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.ViewGroup;

/**
 * Created by Пользователь on 21.06.2017.
 */

public class MpSpinnerView extends ViewGroup {

    private MpSpinnerItems items;
    private MpSpinnerMainView mainView;
    public MpSpinnerView(Context context) {
        super(context);
        init(context);
    }

    public MpSpinnerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MpSpinnerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public MpSpinnerView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {

    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {

    }
}
