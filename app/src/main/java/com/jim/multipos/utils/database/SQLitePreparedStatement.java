package com.jim.multipos.utils.database;

import java.nio.ByteBuffer;

public class SQLitePreparedStatement {
	private boolean isFinalized = false;
	private int sqliteStatementHandle;
	private boolean finalizeAfterQuery = false;

	public int getStatementHandle() {
		return sqliteStatementHandle;
	}

	public SQLitePreparedStatement(AbstractSQLite db, String sql, boolean finalize) throws SQLiteException {
		finalizeAfterQuery = finalize;
		sqliteStatementHandle = prepare(db.getSqliteHandle(), sql);
	}

    public SQLiteCursor query(Object[] args) throws SQLiteException {
        if (args == null) {
            throw new IllegalArgumentException();
        }
        checkFinalized();
        reset(sqliteStatementHandle);
        int i = 1;
        for (Object obj : args) {
            if (obj == null) {
                bindNull(sqliteStatementHandle, i);
            } else if (obj instanceof Integer) {
                bindInt(sqliteStatementHandle, i, (Integer)obj);
            } else if (obj instanceof Double) {
                bindDouble(sqliteStatementHandle, i, (Double)obj);
            } else if (obj instanceof String) {
                bindString(sqliteStatementHandle, i, (String)obj);
            } else {
                throw new IllegalArgumentException();
            }
            i++;
        }

        return new SQLiteCursor(this);
    }

    public SQLiteCursor query() throws SQLiteException {
        checkFinalized();
        reset(sqliteStatementHandle);
        return new SQLiteCursor(this);
    }

    public int step() throws SQLiteException {
        return step(sqliteStatementHandle);
    }

	public void requery() throws SQLiteException {
		checkFinalized();
		reset(sqliteStatementHandle);
	}

	public void dispose() {
		if (finalizeAfterQuery) {
			finalizeQuery();
		}
	}

	void checkFinalized() throws SQLiteException {
		if (isFinalized) {
			throw new SQLiteException("Prepared query finalized");
		}
	}

	public void finalizeQuery() {
        if (isFinalized) {
            return;
        }
		try {
            isFinalized = true;
			finalize(sqliteStatementHandle);
		} catch (SQLiteException e) {
            e.printStackTrace();
		}
	}

    public void bindInteger(int index, int value) throws SQLiteException {
        bindInt(sqliteStatementHandle, index, value);
    }

    public void bindDouble(int index, double value) throws SQLiteException {
        bindDouble(sqliteStatementHandle, index, value);
    }

    public void bindByteBuffer(int index, ByteBuffer value) throws SQLiteException {
        bindByteBuffer(sqliteStatementHandle, index, value, value.limit());
    }

    public void bindString(int index, String value) throws SQLiteException {
        bindString(sqliteStatementHandle, index, value);
    }

    public void bindLong(int index, long value) throws SQLiteException {
        bindLong(sqliteStatementHandle, index, value);
    }

    public void bindNull(int index) throws SQLiteException {
        bindNull(sqliteStatementHandle, index);
    }

	native void bindByteBuffer(int statementHandle, int index, ByteBuffer value, int length) throws SQLiteException;
	native void bindString(int statementHandle, int index, String value) throws SQLiteException;
	native void bindInt(int statementHandle, int index, int value) throws SQLiteException;
    native void bindLong(int statementHandle, int index, long value) throws SQLiteException;
	native void bindDouble(int statementHandle, int index, double value) throws SQLiteException;
	native void bindNull(int statementHandle, int index) throws SQLiteException;
	native void reset(int statementHandle) throws SQLiteException;
	native int prepare(int sqliteHandle, String sql) throws SQLiteException;
	native void finalize(int statementHandle) throws SQLiteException;
    native int step(int statementHandle) throws SQLiteException;
}