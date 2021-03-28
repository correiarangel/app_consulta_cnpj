package br.com.rangeldev.consultacnpj.views.custom

import android.app.Application

import br.com.rangeldev.consultacnpj.R

import uk.co.chrisjenx.calligraphy.CalligraphyConfig

class CustomApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        CalligraphyConfig.initDefault(CalligraphyConfig.Builder()
                .setDefaultFontPath("RobotoSlab-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build())
    }
}