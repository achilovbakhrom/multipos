package com.jim.multipos.utils.managers;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

/**
 * Created by DEV on 26.07.2017.
 */

public class PosFragmentManager {
    private AppCompatActivity activity;
    private FragmentManager fragmentManager;

    @Inject
    public PosFragmentManager(AppCompatActivity activity) {
        this.activity = activity;
        fragmentManager = activity.getSupportFragmentManager();
    }

    public void displayFragment(Fragment fragment, int id) {
        fragmentManager
                .beginTransaction()
                .addToBackStack(null)
                .add(id, fragment)
                .commit();
    }

    public void popBackStack() {
        fragmentManager.popBackStack();
    }
}
