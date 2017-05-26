package com.jim.multipos.utils.database;

import android.content.Context;

public class DatabaseHelper implements Upgradable{
    Context context;
    SQLiteDatabase database;

    static {
        System.loadLibrary("sqlitewrapper");
    }

    public DatabaseHelper(Context context, String name, int version) {
        this.context = context;
        try {
            String fileName = PathGenerator.getDbDir(context) + "/"+PathGenerator.MAIN_DB_NAME;
            database = new SQLiteDatabase(fileName, context, version);
            database.setDbHelper(this);
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public SQLiteCursor query(String sql) throws SQLiteException {
        return database.query(sql);
    }

    public SQLiteCursor query(String sql, Object[] args) throws SQLiteException {
        return database.query(sql, args);
    }

    /**
     * Method be invoked when you chang version of main db (/data/data/com.jim.multipos/multipos.db)
     * @param db database
     * @param from old version of db
     * @param to new version of db
     */
    @Override
    public void OnUpgrade(AbstractSQLite db, int from, int to) { //upgrade version

    }

    public SQLiteDatabase getDatabase() {
        return database;
    }


}
