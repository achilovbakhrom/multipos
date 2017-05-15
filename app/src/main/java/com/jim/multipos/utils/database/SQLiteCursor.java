package com.jim.multipos.utils.database;

import android.util.Log;

import com.jim.multipos.managers.DatabaseManager;

import java.util.Map;

public class SQLiteCursor {

	public static final int SQLITE_INTEGER = 1;
	public static final int SQLITE_FLOAT = 2;
	public static final int SQLITE_STRING = 3;
	public static final int SQLITE_BYTEARRAY = 4;
	public static final int SQLITE_NULL = 5;

	SQLitePreparedStatement preparedStatement;
	boolean inRow = false;

	private Map<String, Integer> pairs;


	public SQLiteCursor(SQLitePreparedStatement stmt) {
		preparedStatement = stmt;
	}

	public boolean isNull(int columnIndex) throws SQLiteException {
		checkRow();
		return columnIsNull(preparedStatement.getStatementHandle(), columnIndex) == 1;
	}

	public int intValue(int columnIndex) throws SQLiteException {
		checkRow();
		return columnIntValue(preparedStatement.getStatementHandle(), columnIndex);
	}

	public double doubleValue(int columnIndex) throws SQLiteException {
		checkRow();
		return columnDoubleValue(preparedStatement.getStatementHandle(), columnIndex);
	}

    public long longValue(int columnIndex) throws SQLiteException {
        checkRow();
        return columnLongValue(preparedStatement.getStatementHandle(), columnIndex);
    }

	public String stringValue(int columnIndex) throws SQLiteException {
		checkRow();
		return columnStringValue(preparedStatement.getStatementHandle(), columnIndex);
	}

	public byte[] byteArrayValue(int columnIndex) throws SQLiteException {
		checkRow();
		return columnByteArrayValue(preparedStatement.getStatementHandle(), columnIndex);
	}

	public int getTypeOf(int columnIndex) throws SQLiteException {
		checkRow();
		return columnType(preparedStatement.getStatementHandle(), columnIndex);
	}

	public boolean next() throws SQLiteException {
		int res = preparedStatement.step(preparedStatement.getStatementHandle());
		if(res == -1) {
            int repeatCount = 6;
            while (repeatCount-- != 0) {
                try {
					Log.d("com.jim.multipos", "sqlite busy, waiting...");
                    Thread.sleep(500);
                    res = preparedStatement.step();
                    if (res == 0) {
                        break;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (res == -1) {
                throw new SQLiteException("sqlite busy");
            }
        }
		inRow = (res == 0);
		return inRow;
	}



	public int getStatementHandle() {
		return preparedStatement.getStatementHandle();
	}

	public void dispose() {
		preparedStatement.dispose();
	}

	void checkRow() throws SQLiteException {
		if (!inRow) {
			throw new SQLiteException("You must call next before");
		}
	}

	native int columnsCount(int statementHandle);
	native int columnType(int statementHandle, int columnIndex);
	native int columnIsNull(int statementHandle, int columnIndex);
	native int columnIntValue(int statementHandle, int columnIndex);
    native long columnLongValue(int statementHandle, int columnIndex);
	native double columnDoubleValue(int statementHandle, int columnIndex);
	native String columnStringValue(int statementHandle, int columnIndex);
	native byte[] columnByteArrayValue(int statementHandle, int columnIndex);

}
