package com.comeze.rangelti.consultacnpj.views.custom;

import android.app.Application;

import com.comeze.rangelti.consultacnpj.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class CustomApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("RobotoSlab-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }
}