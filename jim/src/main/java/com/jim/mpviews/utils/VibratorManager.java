package com.jim.mpviews.utils;

import android.content.Context;
import android.os.Vibrator;

/**
 * Created by Пользователь on 25.05.2017.
 */

public class VibratorManager {
    private Context context;
    public VibratorManager(Context context) {
        this.context = context;
    }

    public void startVibrate(){
        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(100);
    }
}
