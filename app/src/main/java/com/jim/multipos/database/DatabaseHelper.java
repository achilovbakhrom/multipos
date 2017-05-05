package com.jim.multipos.database;

import android.content.Context;

public class DatabaseHelper {
    SQLiteDatabase database;
    public DatabaseHelper(Context context, String name, int version) {
        try {
            database = new SQLiteDatabase(name);

        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }
}
