package com.jim.multipos.utils.database;

import android.content.Context;
import android.util.Log;
import static com.jim.multipos.utils.Constants.TAG;
import java.io.File;

public class PathGenerator {

    public static final String MAIN_DB_NAME = "multipos.db";

    public static String getDbDir(Context context) {
        String path = context.getApplicationInfo().dataDir;
        File dbDir = new File(path, "databases");
        if (!dbDir.exists() && dbDir.mkdirs())
            Log.d(TAG, dbDir.getPath() + " is created successfully...");
        else
            Log.d(TAG, dbDir.getPath() + " is already exists");
        return dbDir.getPath();
    }

    public static String getCacheDir(Context context) {
        String path = context.getApplicationInfo().dataDir;
        File dbDir = new File(path, "files");
        if (!dbDir.exists() && dbDir.mkdirs())
            Log.d(TAG, dbDir.getPath() + " is created successfully...");
        else
            Log.d(TAG, dbDir.getPath() + " is already exists");
        return dbDir.getPath();
    }
}
