package com.jim.multipos;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jim.multipos.database.SQLiteDatabase;
import com.jim.multipos.database.SQLiteException;

public class MultiPOSMain extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_posmain);
        try {
            SQLiteDatabase database = new SQLiteDatabase("test");
            database.beginTransaction();
            database.queryFinalized()
            database.commitTransaction();
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }


}
