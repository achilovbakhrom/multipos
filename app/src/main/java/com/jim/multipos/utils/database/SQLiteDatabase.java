package com.jim.multipos.utils.database;

import android.content.Context;


public class SQLiteDatabase extends AbstractSQLite{
	private Upgradable container;
	public SQLiteDatabase(String dbName, Context context, int version) throws SQLiteException {
		super(dbName, context, version);
	}

	@Override
	void versionChanged(AbstractSQLite db, int from, int to) {
		if (this.container != null)
			this.container.OnUpgrade(db, from, to);
	}

	public void setDbHelper(Upgradable upgradable) {
		this.container = upgradable;
	}

	public void finalize() throws Throwable {
        super.finalize();
		close();
	}
}
