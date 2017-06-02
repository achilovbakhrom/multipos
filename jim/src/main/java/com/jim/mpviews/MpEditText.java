package com.jim.mpviews;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.jim.mpviews.utils.StateSaver;
import com.jim.mpviews.utils.Utils;

/**
 * Created by developer on 16.05.2017.
 */

public class MpEditText extends EditText {
    public MpEditText(Context context) {
        super(context);
        init(context);
    }

    public MpEditText(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MpEditText(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MpEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private void init(Context context){
        setLines(1);
        setTextSize(Utils.convertDpToPixel(8));
        setGravity(RelativeLayout.CENTER_VERTICAL);
        final int sdk = android.os.Build.VERSION.SDK_INT;
        if(sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
            setBackgroundDrawable( ContextCompat.getDrawable(context, R.drawable.edit_text_bg) );
        } else {
            setBackgroundResource( R.drawable.edit_text_bg);
        }
        setPadding((int)Utils.convertDpToPixel(10),(int)Utils.convertDpToPixel(10),(int)Utils.convertDpToPixel(10),(int)Utils.convertDpToPixel(10));
        setHintTextColor(ContextCompat.getColor(context,R.color.colorTextHint));
        setTextColor(ContextCompat.getColor(context,R.color.colorMainText));
    }
    private String key = null;
    public void setState(String key)
    {
        String savedText = StateSaver.getInstance(getContext()).getStateSaver().getString(key, "default");
        this.key = key;
        if (!getText().toString().equals("")){
            setText(savedText);
        }
    }
}
