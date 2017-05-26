package com.jim.multipos;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.jim.mpviews.MpButton;
import com.jim.mpviews.MpCheckbox;
import com.jim.mpviews.MpItem;
import com.jim.mpviews.MpVendorItem;

public class MultiPOSMain extends AppCompatActivity {


    MpButton mpButton;
    MpVendorItem mpVendorItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product_fragment);

        mpButton = (MpButton) findViewById(R.id.btnAdvance);
        mpVendorItem = (MpVendorItem) findViewById(R.id.item);

        mpVendorItem.setVendor("Fish and Bread");
        mpVendorItem.setVendorItem("10");
        mpVendorItem.setVendorName("Lucas Vasquez");
    }


}
