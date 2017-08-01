package com.jim.multipos.common;

import java.util.HashMap;

/**
 * Created by user on 01.08.17.
 */

public abstract class FirstConfigureFragments extends BaseFragment {

    public abstract void checkDatasComplete();
    public abstract HashMap<String,String> getDatas();

}
