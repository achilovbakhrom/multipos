package com.jim.multipos.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.jim.mpviews.MpButton;
import com.jim.multipos.R;

import butterknife.BindView;

/**
 * Created by DEV on 01.08.2017.
 */

public class WarningDialog extends Dialog {
    @BindView(R.id.btnWarningOK)
    MpButton btnWarningOK;
    @BindView(R.id.tvWarningText)
    TextView tvWarningText;
    public WarningDialog(@NonNull Context context) {
        super(context);
    }

    public WarningDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    protected WarningDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.warning_dialog);
    }

    public void setWarningText(String warningText){
        tvWarningText.setText(warningText);
    }

    public void  setOnOKClickListener(View.OnClickListener listener) {
        btnWarningOK.setOnClickListener(listener);
    }
}
