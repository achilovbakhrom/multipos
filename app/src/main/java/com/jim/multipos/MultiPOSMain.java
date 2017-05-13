package com.jim.multipos;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jim.multipos.utils.database.DatabaseHelper;

public class MultiPOSMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_posmain);
        Log.d("com.jim.multipos", "onCreate: "+getDatabasePath("testtest.db").getPath());
        DatabaseHelper helper = new DatabaseHelper(this, "test.db", 4);



//        fqClassName fqClassName = new fqClassName();
//        fqClassName.testField = "privet kak dela";
//        Log.d("sss", "onCreate: " + fqClassName.testField);
//        try {
//            SQLiteDatabase database = new SQLiteDatabase("test");
//            database.beginTransaction();
//
//            database.commitTransaction();
//        } catch (SQLiteException e) {
//            e.printStackTrace();
//        }
    }


}
