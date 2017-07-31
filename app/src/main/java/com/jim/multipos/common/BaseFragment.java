package com.jim.multipos.common;

import android.support.v4.app.Fragment;

import com.jim.multipos.di.HasComponent;

/**
 * Created by DEV on 27.07.2017.
 */

public abstract class BaseFragment extends Fragment {
    @SuppressWarnings("unchecked")
    public  <T> T getComponent(Class<T> componentType){
        return componentType.cast(((HasComponent<T>)getActivity()).getComponent());
    }
}
