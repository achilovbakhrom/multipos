package com.jim.multipos.managers;

import android.content.Context;

import com.jim.multipos.Constants;
import com.jim.multipos.utils.database.DatabaseHelper;
import com.jim.multipos.utils.database.SQLiteCursor;
import com.jim.multipos.utils.database.SQLiteException;

/**
 * Created by Developer on 5/13/17.
 */

public class DatabaseManager {

    private DatabaseHelper helper;
    private static DatabaseManager manager;
    private Context context;

    public static DatabaseManager getInstance(Context context) {
        if (manager == null) {
            manager = new DatabaseManager(context);
        }
        return manager;
    }

    private DatabaseManager(Context context) {
        this.context = context;
        helper = new DatabaseHelper(context, Constants.DB_NAME, 1);
        initPragmas();
    }

    private void initPragmas() {
        execSQL("PRAGMA foreign_keys = ON");
    }

    public void beginTransaction() {
        helper.getDatabase().beginTransaction();
    }

    public void commitTransaction() {
        helper.getDatabase().commitTransaction();
    }

    public SQLiteCursor query(String sql) {
        SQLiteCursor cursor = null;
        try {
            cursor = helper.query(sql);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return cursor;
    }

    public SQLiteCursor query(String sql, Object[] args) {
        SQLiteCursor cursor = null;
        try {
            cursor = helper.query(sql, args);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
        return cursor;
    }

    public void execSQL(String sql) {
        try {
            helper.getDatabase().execSQL(sql);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }


}
