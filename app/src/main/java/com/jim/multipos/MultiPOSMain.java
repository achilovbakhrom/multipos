package com.jim.multipos;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jim.multipos.utils.database.SQLiteCursor;
import com.jim.multipos.utils.database.SQLiteException;

public class MultiPOSMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_posmain);
        SQLiteCursor cursor = null;
        try {
            while(cursor.next() ){
                cursor.intValue(2);
            }
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }


}
