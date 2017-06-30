package com.jim.mpviews;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.preference.PreferenceManager;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.jim.mpviews.utils.StateSaver;
import com.jim.mpviews.utils.Test;
import com.jim.mpviews.utils.Utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by developer on 20.05.2017.
 */

public class MpSpinner extends RelativeLayout {
    Spinner spinner;
    ImageView imageView;
    LinearLayout linearLayout;
    MpSpinnerAdapter adapter;
    ArrayList<String> arrayList;
    private String withDefaultValue = null;

    public MpSpinner(Context context) {
        super(context);
        init(context, null);
    }

    public MpSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MpSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MpSpinner(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    Context context;

    private void init(Context context, AttributeSet attrs) {
        Log.d(Test.TAG, "init");
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.mp_spinner, this);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(layoutParams);
        TypedArray attributeArray = context.obtainStyledAttributes(attrs, R.styleable.MpSpinner);
        spinner = (Spinner) findViewById(R.id.spProductQuantity);
        imageView = (ImageView) findViewById(R.id.mpSpIcon);
        linearLayout = (LinearLayout) findViewById(R.id.mpSpinnerLayout);
        arrayList = new ArrayList<>();
        linearLayout.setBackgroundResource(attributeArray.getResourceId(R.styleable.MpSpinner_sp_bg, R.drawable.edit_text_bg));
        imageView.setImageResource(attributeArray.getResourceId(R.styleable.MpSpinner_img_color, R.drawable.triangle));
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                spinner.performClick();
            }
        });

    }

    public void setItems(ArrayList<String> items) {
        arrayList = items;
        adapter.notifyDataSetChanged();
    }

    public void setItems(String[] items) {
        for (String s : items)
            arrayList.add(s);

    }

    public void setAdapter() {
        adapter = new MpSpinnerAdapter(context, R.layout.mp_spinner_adapter);
        spinner.setAdapter(adapter);
    }

    private String unPickValue = null;

    public void setUnPickValue(String unPickValue) {
        this.unPickValue = unPickValue;
        arrayList.add(0, unPickValue);

    }

    public void setWithDefaultValueForFirstTime(String defaultValue) {
        withDefaultValue = defaultValue;
        arrayList.add(0, "");
    }

    private String key = null;

    public void setState(String key) {
        int position = StateSaver.getInstance(getContext()).getStateSaver().getInt(key, 0);
        this.key = key;
        Log.d(Test.TAG, "state :" + String.valueOf(position));
        if (spinner.getAdapter().getCount() < position) {
            unselected = false;
            spinner.setSelection(0);
        } else {
            unselected = false;
            spinner.setSelection(position);
        }

    }

    MpSpinner.setOnItemClickListener onItemClickListener;
    boolean firstChangedItem = true;

    public void setOnItemSelectedListener(MpSpinner.setOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (firstChangedItem) firstChangedItem = false;
                else {
                    Log.d(Test.TAG, "item selected: " + String.valueOf(i));
                    if (key != null) if (!key.isEmpty())
                        StateSaver.getInstance(getContext()).getStateSaver().edit().putInt(key, i).apply();
                    MpSpinner.this.onItemClickListener.onItemSelected(adapterView, view, i, l);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    public interface setOnItemClickListener {
        void onItemSelected(AdapterView<?> adapterView, View view, int i, long l);
    }

    private boolean unselected = true;

    public int selectedItem() {
        int selected = spinner.getSelectedItemPosition();
        if (withDefaultValue != null)
            selected--;
        if (unPickValue != null)
            selected--;
        if (selected < 0) selected = -1;
        return selected;
    }

    private class MpSpinnerAdapter extends ArrayAdapter {
        LayoutInflater inflater;
        Context context;
        TextView textView;

        public MpSpinnerAdapter(Context context, int resouceId) {
            super(context, resouceId, arrayList);
            this.context = context;
            inflater = ((Activity) context).getLayoutInflater();
        }

        @Override
        public View getDropDownView(int position, View cnvtView, ViewGroup prnt) {
            return getCustomView(position, prnt);
        }

        @Override
        public View getView(int pos, View cnvtView, ViewGroup prnt) {
            return getForDefault(pos, prnt);
        }

        public View getForDefault(int position, ViewGroup parent) {
            textView = new TextView(context);
            textView.setTextSize(Utils.convertDpToPixel(14));
            if (withDefaultValue != null && unselected) {
                textView.setText(withDefaultValue);
                textView.setTextColor(Color.parseColor("#b9b9b9"));
                unselected = false;
            } else if (arrayList.size() != 0) {
                textView.setText(arrayList.get(position));
                textView.setTextColor(Color.parseColor("#212121"));
            } else {
                textView.setText("");
            }
            return textView;
        }

        public View getCustomView(int position, ViewGroup parent) {
            if (arrayList.get(position).equals(""))
                return inflater.inflate(R.layout.emp, parent, false);
            View mySpinner = inflater.inflate(R.layout.mp_spinner_adapter, parent, false);
            TextView textView = (TextView) mySpinner.findViewById(R.id.spinnerTextView);
            textView.setText(arrayList.get(position));
            mySpinner.setAlpha(0.1f);
            mySpinner.animate().alpha(1f).setDuration(400).start();
            return mySpinner;
        }
// implementation of mpSpinner
//        mpSpinner1 = (MpSpinner) findViewById(R.id.mpSpinner1);
//        mpSpinner1.setUnPickValue("default");
//        mpSpinner1.setWithDefaultValueForFirstTime("(set value)");
//        mpSpinner1.setItems(new String[]{"Uzs","Dollar"});
//        mpSpinner1.setAdapter();
////        mpSpinner1.setState(MpSpinnerState.GROUP_CURRENCY);
//        mpSpinner1.setOnItemSelectedListener(new MpSpinner.setOnItemClickListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Log.d(Test.TAG, "onItemClick: "+mpSpinner1.selectedItem());
//            }
//        });


    }

}
