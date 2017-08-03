package com.jim.multipos.ui.first_configure.presenters;

import android.util.Log;

import com.jim.multipos.ui.first_configure.fragments.PosDetailsFragment;
import com.jim.multipos.ui.first_configure.fragments.PosDetailsFragmentView;

import java.util.HashMap;

/**
 * Created by user on 01.08.17.
 */

public class PosFragmentPresenterImpl implements PosFragmentPresenter {
    private PosDetailsFragmentView view;

    @Override
    public void init(PosDetailsFragmentView view) {
        this.view = view;
    }

    @Override
    public void displayFragment() {
        view.displayFragment(new PosDetailsFragment());
    }

    @Override
    public void checkData() {
        HashMap<String, String> datas = view.getDatas();

        if (datas.get("posId").isEmpty()) {
            view.showCheckedDatasResult(false);
            return;
        }

        if (datas.get("alias").isEmpty()) {
            view.showCheckedDatasResult(false);
            return;
        }

        if (datas.get("address").isEmpty()) {
            view.showCheckedDatasResult(false);
            return;
        }

        if (datas.get("password").isEmpty()) {
            view.showCheckedDatasResult(false);
            return;
        }

        if (datas.get("repeatPassword").isEmpty()) {
            view.showCheckedDatasResult(false);
            return;
        }

        view.showCheckedDatasResult(true);
    }
}
