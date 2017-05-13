package com.jim.multipos.utils.database;

/**
 * Created by Developer on 5/10/17.
 */

public interface Upgradable {
    void OnUpgrade(AbstractSQLite db, int from, int to);
}
