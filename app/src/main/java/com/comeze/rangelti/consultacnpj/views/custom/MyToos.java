package com.comeze.rangelti.consultacnpj.views.custom;

import android.content.Context;


import androidx.appcompat.app.AppCompatActivity;

public class MyToos extends AppCompatActivity {

    private Context context;


    public MyToos(Context context) {
        this.context = context;
    }

    //trata string
    public String beautifyCNPJ(String doc) {
        doc = doc.replace ( ".", "" );
        doc = doc.replace ( "/", "" );
        doc = doc.replace ( "-", "" );
        //startMsg ( String.format ( "%s", doc ) );
        return doc;
    }
}
