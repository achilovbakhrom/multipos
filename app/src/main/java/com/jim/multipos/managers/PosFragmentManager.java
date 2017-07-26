package com.jim.multipos.managers;

import android.app.Activity;
import android.app.FragmentManager;

/**
 * Created by DEV on 26.07.2017.
 */

public class PosFragmentManager {
    private Activity activity;
    private FragmentManager fragmentManager;

    public PosFragmentManager(Activity activity) {
        this.activity = activity;
        fragmentManager = activity.getFragmentManager();
    }

    public void displayFragment(android.app.Fragment fragment, int id) {
        fragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .replace(id, fragment)
                .commit();
    }
}
