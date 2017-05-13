package com.jim.multipos.utils.database;

import android.content.Context;
import android.util.Log;

/**
 * Created by Developer on 5/10/17.
 */

public abstract class AbstractSQLite {

    static {
        System.loadLibrary("sqlitewrapper");
    }

    private final int sqliteHandle;
    private boolean isOpen = false;
    private boolean inTransaction = false;
    private int version;

    public AbstractSQLite(String dbName, Context context, int version) throws SQLiteException {
        this.version = version;
        sqliteHandle = opendb(dbName, PathGenerator.getCacheDir(context), Integer.toString(version));
        isOpen = true;
    }

    public int getVersion() {
        return version;
    }

    public int getSqliteHandle() {
        return sqliteHandle;
    }

    public void beginTransaction() {
        beginTransaction(sqliteHandle);
        inTransaction = true;
    }

    public void commitTransaction() {
        inTransaction = false;
        commitTransaction(sqliteHandle);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        closedb(sqliteHandle);
    }

    abstract void versionChanged(AbstractSQLite db, int from, int to);

    void versionChangeNotification(int from, int to) {
        versionChanged(this, from, to);
    }

    public SQLiteCursor query(String sql) throws SQLiteException {
        checkOpened();
        SQLiteCursor cursor = new SQLitePreparedStatement(this, sql, true).query();
        return cursor;
    }

    public void execSQL(String sql) throws SQLiteException {
        execSQL(sqliteHandle, sql);
    }


    public SQLiteCursor query(String sql, Object... args) throws SQLiteException {
        checkOpened();
        SQLiteCursor cursor = new SQLitePreparedStatement(this, sql, true).query(args);
        return cursor;
    }

    public int columnIndex(String tableName, String columnName) throws SQLiteException {
        return columnIndex(sqliteHandle, tableName, columnName);
    }

    void checkOpened() throws SQLiteException {
		if (!isOpen) {
			Log.e("com.jim.multipos", "first open the database - for it you must call opendb() method");
			throw new SQLiteException("Database closed");
		}
	}

    public void close() {
		if (isOpen) {
			try {
                commitTransaction();
				closedb(sqliteHandle);
			} catch (SQLiteException e) {
                e.printStackTrace();
			}
			isOpen = false;
		}
	}

    native int opendb(String fileName, String cacheDir, String version) throws SQLiteException;
    native void closedb(int sqliteHandle) throws SQLiteException;
    native void execSQL(int sqliteHandle, String sql) throws SQLiteException;
    native void beginTransaction(int sqliteHandle);
    native void commitTransaction(int sqliteHandle);
    native void tableinfo(int sqliteHandle, String tableName);
    native int columnIndex(int sqliteHandle, String tableName, String columnName) throws SQLiteException;

}
