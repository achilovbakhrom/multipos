package com.jim.mpviews;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by DEV on 30.06.2017.
 */

public class MpSearchView extends RelativeLayout {
    private ImageView mpBarcodeImage, mpSearchImage;
    private boolean visibility_status = true;

    public MpSearchView(Context context) {
        super(context);
        init(context, null);
    }

    public MpSearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MpSearchView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public MpSearchView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    public void init(Context context, AttributeSet attributeSet) {
        LayoutInflater.from(context).inflate(R.layout.mp_search_view, this);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(layoutParams);
        setBackgroundResource(R.drawable.edit_text_bg);
        TypedArray attributeArray = context.obtainStyledAttributes(attributeSet, R.styleable.MpSearchView);
        mpBarcodeImage = (ImageView) findViewById(R.id.mpBarcodeImage);
        mpSearchImage = (ImageView) findViewById(R.id.mpSearchImage);
        visibility_status = attributeArray.getBoolean(R.styleable.MpSearchView_barcode_visibility, true);
        if (visibility_status) {
            mpBarcodeImage.setVisibility(VISIBLE);
        } else {
            mpBarcodeImage.setVisibility(GONE);
        }

        attributeArray.recycle();
    }

}
