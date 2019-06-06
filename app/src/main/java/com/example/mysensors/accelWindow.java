package com.example.mysensors;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

/*
* Pops up to explain accelerometer readings to user.
* Author: Martin Gasiorowski
* For educational purposes only, all rights reserved.
* */
public class accelWindow extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init layout for popup window
        setContentView(R.layout.popupwindow);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout(width, height - 30);
    }
}
