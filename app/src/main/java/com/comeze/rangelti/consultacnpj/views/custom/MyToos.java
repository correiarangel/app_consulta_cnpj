package com.comeze.rangelti.consultacnpj.views.custom;

import android.content.Context;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import com.comeze.rangelti.consultacnpj.views.rest.CnpjEmpRest;


public class MyToos extends AppCompatActivity {

    private Context context;
    private MsgStatus msg;

    public MyToos(Context context) {
        this.context = context;
        msg = new MsgStatus( this.context );
    }

    //trata string
    public String beautifyCNPJ(String doc) {
        doc = doc.replace ( ".", "" );
        doc = doc.replace ( "/", "" );
        doc = doc.replace ( "-", "" );
        //startMsg ( String.format ( "%s", doc ) );
        return doc;
    }
    //captura cnpj e envia como parametro
    // para cnpjEmpRest
    public void getCnpj (EditText edt, CnpjEmpRest rest ) {

        String cnpj = beautifyCNPJ ( edt.getText ( ).toString ( ) );

        if ( cnpj.equals ( "" ) ) {
            msg.startMsg ( "Não é possivel fazer busca sem um CNPJ!" );
            msg.startMsg ( "Por favor, digite um CNPJ para consulta." );
        } else if( cnpj.length() < 14 ){
            msg.startMsg ( "CNPJ o composto por 14 numeros " );
            msg.startMsg("Você digitou :"+cnpj.length() +" numeros.");
            msg.startMsg ( "Digite um CNPJ VALIDO !" );
        } else {
            try {
                rest.listCnpj ( cnpj.trim ( ) );
            } catch ( Exception err ) {
                msg.startMsg ( "Ocorreu erro ao busca o CNPJ !..." );
            }
        }
    }


}//end class
