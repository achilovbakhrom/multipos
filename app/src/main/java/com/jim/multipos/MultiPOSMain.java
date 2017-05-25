package com.jim.multipos;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.jim.mpviews.MpButton;

public class MultiPOSMain extends AppCompatActivity {


    MpButton mpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product_fragment);

        mpButton = (MpButton) findViewById(R.id.btnAdvance);

    }


}
