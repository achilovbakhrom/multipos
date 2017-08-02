package com.jim.multipos.ui.first_configure.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.jakewharton.rxbinding2.view.RxView;
import com.jim.multipos.R;
import com.jim.multipos.ui.first_configure.FirstConfigureActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by user on 27.07.17.
 */

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.ViewHolder> {
    private FirstConfigureActivity activity;
    private String[] title;
    private String[] description;
    private int prevPosition;

    public SettingsAdapter(FirstConfigureActivity activity) {
        this.activity = activity;
        Resources resource = activity.getResources();
        title = resource.getStringArray(R.array.start_configuration_title);
        description = resource.getStringArray(R.array.start_configuration_desctiption);
        prevPosition = 0;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvTitle.setText(title[position]);
        holder.tvDescription.setText(description[position]);

        if (prevPosition == position) {
            holder.flStrip.setVisibility(View.VISIBLE);
            holder.itemView.setBackgroundColor(activity.getColor(R.color.colorWhite));

        } else {
            holder.flStrip.setVisibility(View.INVISIBLE);
            holder.itemView.setBackgroundColor(activity.getColor(R.color.colorBackgroundGrey));
        }
    }

    @Override
    public int getItemCount() {
        return title.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tvTitle)
        TextView tvTitle;
        @BindView(R.id.tvDescription)
        TextView tvDescription;
        @BindView(R.id.strip)
        FrameLayout flStrip;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            RxView.clicks(itemView).subscribe(new Observer<Object>() {
                @Override
                public void onSubscribe(@NonNull Disposable d) {

                }

                @Override
                public void onNext(@NonNull Object o) {
                    notifyItemChanged(getAdapterPosition());
                    notifyItemChanged(prevPosition);
                    prevPosition = getAdapterPosition();
                    activity.replaceFragment(prevPosition);
                }

                @Override
                public void onError(@NonNull Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
        }
    }
}
