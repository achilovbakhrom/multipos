package com.jim.multipos;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jim.mpviews.calendar.MpCalendarView;
import com.jim.multipos.managers.DatabaseManager;
public class MultiPOSMain extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.linked_vendors_fragment);
        DatabaseManager.getInstance(this).execSQL("CREATE TABLE IF NOT EXISTS test(id INTEGER PRIMARY KEY, name TEXT);");

    }


}
