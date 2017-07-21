package com.jim.mpviews;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static com.jim.mpviews.utils.Constants.DEFAULT_MODE;
import static com.jim.mpviews.utils.Constants.MAIN_MODE;
import static com.jim.mpviews.utils.Constants.REG_MODE;
import static com.jim.mpviews.utils.Constants.SEARCH_MODE;

/**
 * Created by DEV on 30.06.2017.
 */

public class MpToolbar extends RelativeLayout {

    private int mode = DEFAULT_MODE;
    private boolean isPressed = false;
    private LinearLayout mpMainMenu;
    private LinearLayout mpSearch;
    private RelativeLayout mpLeftSide, mpRightSide;
    private ImageView mpSettings, mpProfilePhoto;
    private TextView mpEmpName, mpEmpRole;
    private MpHorizontalScroller mpHorizontalScroller;
    private MpSearchView mpSearchView;

    public MpToolbar(Context context) {
        super(context);
        init(context);
    }

    public MpToolbar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MpToolbar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public MpToolbar(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.mp_toolbar, this);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        setLayoutParams(layoutParams);
        mpMainMenu = (LinearLayout) findViewById(R.id.mpMainMenu);
        mpSearch = (LinearLayout) findViewById(R.id.mpSearch);
        mpSettings = (ImageView) findViewById(R.id.mpSettings);
        mpProfilePhoto = (ImageView) findViewById(R.id.mpProfilePhoto);
        mpEmpName = (TextView) findViewById(R.id.mpEmpName);
        mpEmpRole = (TextView) findViewById(R.id.mpEmpRole);
        mpHorizontalScroller = (MpHorizontalScroller) findViewById(R.id.mpHorRoller);
        mpSearchView = (MpSearchView) findViewById(R.id.mpSearchView);
        mpLeftSide = (RelativeLayout) findViewById(R.id.mpLeftSide);
        mpRightSide = (RelativeLayout) findViewById(R.id.mpRightSide);
        setMode(mode);
    }

    public void setOnProductClickListener(OnClickListener productClickListener) {
        findViewById(R.id.mpProducts).setOnClickListener(productClickListener);
    }

    public void setOnEmployerClickListener(OnClickListener employerClickListener) {
        findViewById(R.id.mpEmployers).setOnClickListener(employerClickListener);
    }

    public void setOnInventoryClickListener(OnClickListener inventoryClickListener) {
        findViewById(R.id.mpInventory).setOnClickListener(inventoryClickListener);
    }

    public void setOnCustomerClickListener(OnClickListener customerClickListener) {
        findViewById(R.id.mpCustomers).setOnClickListener(customerClickListener);
    }

    public void setOnReportClickListener(OnClickListener reportClickListener) {
        findViewById(R.id.mpCustomers).setOnClickListener(reportClickListener);
    }

    public void setOnOrderClickListener(OnClickListener orderClickListener) {
        mpHorizontalScroller.setOnItemClickListener(orderClickListener);
    }

    public void setOnSearchClickListener(OnClickListener searchClickListener) {
        mpSearch.setOnClickListener(searchClickListener);
    }

    public void setOnSettingsClickListener(OnClickListener settingsClickListener) {
        mpSettings.setOnClickListener(settingsClickListener);
    }

    public void setOnProfileCliclListener(OnClickListener profileCliclListener) {
        findViewById(R.id.mpProfile).setOnClickListener(profileCliclListener);
    }

    public void setInfoClickListener(OnClickListener infoClickListener) {
        findViewById(R.id.mpInfo).setOnClickListener(infoClickListener);
    }

    public void setMode(int mode) {
        this.mode = mode;
        setVisibility();
        invalidate();
    }

    private void setVisibility() {
        switch (mode) {
            case DEFAULT_MODE: {
                mpMainMenu.setVisibility(GONE);
                mpSettings.setVisibility(GONE);
                mpHorizontalScroller.setVisibility(GONE);
                mpSearchView.setVisibility(GONE);
                break;
            }
            case MAIN_MODE: {
                mpMainMenu.setVisibility(VISIBLE);
                mpSettings.setVisibility(VISIBLE);
                mpHorizontalScroller.setVisibility(VISIBLE);
                mpSearchView.setVisibility(GONE);
                break;
            }
            case SEARCH_MODE: {
                mpMainMenu.setVisibility(GONE);
                mpSettings.setVisibility(GONE);
                mpHorizontalScroller.setVisibility(GONE);
                mpSearchView.setVisibility(VISIBLE);
                break;
            }
            case REG_MODE: {
                mpMainMenu.setVisibility(GONE);
                mpSettings.setVisibility(GONE);
                mpHorizontalScroller.setVisibility(GONE);
                mpSearchView.setVisibility(GONE);
                break;
            }
        }
    }

    public void setName(String name) {
        mpEmpName.setText(name);
        invalidate();
    }

    public void setRole(String role) {
        mpEmpRole.setText(role);
        invalidate();
    }

    public void setProfilePhoto(Drawable profilePhoto) {
        mpProfilePhoto.setImageDrawable(profilePhoto);
    }
}
