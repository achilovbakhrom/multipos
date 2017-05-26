package com.jim.multipos.entity;

import android.content.Context;

/**
 * Created by Developer on 5/22/17.
 */

public interface Entity {
    void saveOrUpdate(Context context);
    void delete(Context context);
    void createTable(Context context);
}
