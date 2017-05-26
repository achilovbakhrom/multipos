package com.jim.multipos;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jim.multipos.managers.DatabaseManager;
import com.jim.multipos.utils.database.DatabaseHelper;
import com.jim.multipos.utils.database.SQLiteCursor;
import com.jim.multipos.utils.database.SQLiteException;

public class MultiPOSMain extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_posmain);
        DatabaseManager.getInstance(this).execSQL("CREATE TABLE IF NOT EXISTS test(id INTEGER PRIMARY KEY, name TEXT);");
    }


}
