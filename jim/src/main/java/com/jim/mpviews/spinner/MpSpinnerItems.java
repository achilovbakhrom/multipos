package com.jim.mpviews.spinner;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jim.mpviews.R;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * Created by Пользователь on 21.06.2017.
 */

public class MpSpinnerItems extends LinearLayout {

    private RecyclerView recyclerView;
    private MpSpinnerAdapter mpSpinnerAdapter;
    private ArrayList<String> arrayList;
    Context context;

    public MpSpinnerItems(Context context) {
        super(context);
        init(context);
    }

    public MpSpinnerItems(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MpSpinnerItems(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public MpSpinnerItems(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        this.context = context;
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        arrayList = new ArrayList<>();
        recyclerView.setLayoutParams(layoutParams);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        addView(recyclerView);
    }

    public void setAdapter() {
        mpSpinnerAdapter = new MpSpinnerAdapter(context);
        recyclerView.setAdapter(mpSpinnerAdapter);
    }

    public void setItems(ArrayList<String> items) {
        arrayList = items;
        mpSpinnerAdapter.notifyDataSetChanged();
    }

    public void setItems(String[] items) {
        arrayList.addAll(Arrays.asList(items));
    }

    protected void openToUp() {

    }

    private class MpSpinnerAdapter extends RecyclerView.Adapter<MpSpinnerAdapter.SpinnerViewHolder> {
        private Context context;

        public MpSpinnerAdapter(Context context) {
            this.context = context;
        }

        @Override
        public SpinnerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mp_spinner_adapter, parent, false);
            return new SpinnerViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MpSpinnerAdapter.SpinnerViewHolder holder, int position) {
            holder.textView.setText(arrayList.get(position));
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        class SpinnerViewHolder extends RecyclerView.ViewHolder {
            TextView textView;

            private SpinnerViewHolder(View itemView) {
                super(itemView);
                textView = (TextView) itemView.findViewById(R.id.spinnerTextView);
            }
        }
    }
}
