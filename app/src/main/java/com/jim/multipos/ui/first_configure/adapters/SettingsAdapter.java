package com.jim.multipos.ui.first_configure.adapters;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;


import com.jakewharton.rxbinding.view.RxView;
import com.jim.mpviews.MpCheckbox;
import com.jim.multipos.R;
import com.jim.multipos.ui.first_configure.presenters.FirstConfigureLeftSidePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.functions.Action1;

/**
 * Created by user on 27.07.17.
 */

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.ViewHolder> {
    private String[] title;
    private String[] description;
    private int prevPosition;
    private Context context;
    private FirstConfigureLeftSidePresenter firstConfigureLeftSidePresenter;

    public SettingsAdapter(Context context, FirstConfigureLeftSidePresenter firstConfigureLeftSidePresenter) {
        this.firstConfigureLeftSidePresenter = firstConfigureLeftSidePresenter;
        this.context = context;
        Resources resource = context.getResources();
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
        holder.chbDone.setCheckBoxClickable(false);
        holder.chbDone.setCheckBoxEnabled(false);
        holder.tvTitle.setText(title[position]);
        holder.tvDescription.setText(description[position]);

        if (prevPosition == position) {
            holder.flStrip.setVisibility(View.VISIBLE);
            holder.itemView.setBackgroundColor(context.getColor(R.color.colorWhite));

        } else {
            holder.flStrip.setVisibility(View.INVISIBLE);
            holder.itemView.setBackgroundColor(context.getColor(R.color.colorBackgroundGrey));
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
        @BindView(R.id.chbDone)
        MpCheckbox chbDone;

        public ViewHolder(final View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            RxView.clicks(itemView).subscribe(new Action1<Void>() {
                @Override
                public void call(Void aVoid) {
                    notifyItemChanged(getAdapterPosition());
                    notifyItemChanged(prevPosition);
                    prevPosition = getAdapterPosition();
                    firstConfigureLeftSidePresenter.replaceFragment(prevPosition);
                }
            });

            /*RxView.clicks(itemView).subscribe(new Observer<Object>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Object o) {
                    notifyItemChanged(getAdapterPosition());
                    notifyItemChanged(prevPosition);
                    prevPosition = getAdapterPosition();
                    *//*activity.replaceFragment(prevPosition);*//*
                    firstConfigureLeftSidePresenter.replaceFragment(prevPosition);
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });*/
        }

        public void setCheckBoxIndicator(boolean checked) {
            chbDone.setChecked(checked);
        }
    }
}
