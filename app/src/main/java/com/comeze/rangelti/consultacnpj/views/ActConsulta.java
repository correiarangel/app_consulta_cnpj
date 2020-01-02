package com.comeze.rangelti.consultacnpj.views;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.comeze.rangelti.consultacnpj.R;
import com.comeze.rangelti.consultacnpj.views.adpter.CnpjEmpresaAdapter;
import com.comeze.rangelti.consultacnpj.views.custom.CreatePDF;
import com.comeze.rangelti.consultacnpj.views.custom.MsgStatus;
import com.comeze.rangelti.consultacnpj.views.custom.MyToos;
import com.comeze.rangelti.consultacnpj.views.model.CnpjEmpresa;
import com.comeze.rangelti.consultacnpj.views.rest.CnpjEmpRest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class ActConsulta extends AppCompatActivity implements View.OnClickListener {

    private static final int PERMISSION_REQUEST_CODE = 200;

    private EditText edtCNPJ;
    private Button btnPesquisar;
    private CnpjEmpresa empresa;
    private List< CnpjEmpresa > cnpjEmpresas;
    private FloatingActionButton fltBtnPrint;
    private FloatingActionButton fltBtnInfo;
    private FloatingActionButton fltBtnSair;
    private ListView lvCNPJ;
    private CreatePDF createPDF;
    private CnpjEmpRest cnpjEmpRest;
    private CnpjEmpresaAdapter empresaAdapter;

    private MsgStatus msg;
    private MyToos tools;

    private AdapterView.OnItemClickListener selecionarCnpj =
            new AdapterView.OnItemClickListener ( ) {

        public void onItemClick ( AdapterView< ? > arg0, View arg1, int pos, long id ) {

            try {
                empresa = ( CnpjEmpresa ) cnpjEmpRest.getPla ( ).getItem ( pos );
                //method decision
                alertPrint( empresa );

            } catch ( Exception e ) {
                e.printStackTrace ( );
            }
        }
    };

    @Override
    protected void onCreate ( Bundle savedInstanceState ) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.act_consulta );

        setRequestedOrientation ( ActivityInfo.SCREEN_ORIENTATION_PORTRAIT );  //Trava a rotaçãø da tela

        startComponent ( );
        lvCNPJ.setOnItemClickListener ( selecionarCnpj );
        //call method read write
        testePermissoes();

        System.clearProperty("MSG");
        System.clearProperty("KEY_NAME_FILE");
    }

    public void startComponent ( ) {

        fltBtnInfo = findViewById(R.id.fltBtnInfo);
        fltBtnPrint = findViewById ( R.id.fltBtnPrint );
        fltBtnSair = findViewById( R.id.fltBtnSair);
        edtCNPJ = findViewById ( R.id.edtCNPJ );
        btnPesquisar = findViewById ( R.id.btnPesquisar );
        lvCNPJ = findViewById ( R.id.lvCNPJ );
        //empresaAdapter e iniciado em clearComponet()
        clearComponet();

        cnpjEmpRest = new CnpjEmpRest ( this, lvCNPJ );

        btnPesquisar.setOnClickListener( this );
        fltBtnPrint.setOnClickListener( this );
        fltBtnInfo.setOnClickListener( this );
        fltBtnSair.setOnClickListener( this );

        msg = new MsgStatus(getApplicationContext());
        tools = new MyToos(getApplicationContext());
    }
    //metudo que sobreescreve fontes coistomiza fonte
    @Override
    protected void attachBaseContext ( Context newBase ) {
        super.attachBaseContext ( CalligraphyContextWrapper.wrap ( newBase ) );
    }
    @Override
    public void onBackPressed()
    {
      msg.startMsg("Saindo...");
      finish();
    }
    /* Metudos verificam permissão de leitura e gravação em disco
     * Em tempo de execução só funcionou com  checkPermission
     * e requestPermissionAndContinue
     */
    private boolean checkPermission() {

        return ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                ;
    }

    private void requestPermissionAndContinue() {
        if (ContextCompat.checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this, WRITE_EXTERNAL_STORAGE)
                    && ActivityCompat.shouldShowRequestPermissionRationale(this, READ_EXTERNAL_STORAGE)) {
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
                alertBuilder.setCancelable(true);
                alertBuilder.setTitle(getString(R.string.permission_necessary));
                alertBuilder.setMessage(R.string.storage_permission_is_encessary_to_wrote_event);
                alertBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityCompat.requestPermissions(ActConsulta.this, new String[]{WRITE_EXTERNAL_STORAGE
                                , READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
                    }
                });
                AlertDialog alert = alertBuilder.create();
                alert.show();
                Log.e("", "permission denied, show dialog");
            } else {
                ActivityCompat.requestPermissions(ActConsulta.this, new String[]{WRITE_EXTERNAL_STORAGE,
                        READ_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
            }
        } else {
            openActivity();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == PERMISSION_REQUEST_CODE) {
            if (permissions.length > 0 && grantResults.length > 0) {

                boolean flag = true;
                for (int i = 0; i < grantResults.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        flag = false;
                    }
                }
                if (flag) {
                    openActivity();
                } else {
                    finish();
                }

            } else {
                finish();
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    private void openActivity() {
        //add your further process after giving permission or to
        // download images from remote server.
    }
    //Call method permissão read write in disk
    private void testePermissoes(){
        if (!checkPermission()) {
            openActivity();
        } else {
            if (checkPermission()) {
                requestPermissionAndContinue();
            } else {
                openActivity();
            }
        }
    }
    // FIM  Metudos verificam permissão
    @Override
    public void onClick ( View v ) {
        if ( v.getId ( ) == R.id.btnPesquisar )
        {
            startVibrat ( 90 );
            tools.getCnpj (edtCNPJ,cnpjEmpRest );
            hideKeyboard( v );

        } else if ( v.getId ( ) == R.id.fltBtnPrint ) {

            startVibrat ( 90 );

            int quant_Pos_list =  lvCNPJ.getAdapter().getCount();
            if ( quant_Pos_list >=1 )
            {
                empresa = ( CnpjEmpresa ) cnpjEmpRest.getPla ( ).getItem ( 0 );

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

                    createPDF = new CreatePDF( empresa );

                    try {
                        createPDF.gerar();
                        clearComponet();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    msg.setMsg();
                } else {
                    msg.startMsg("Dispositivo incompatível com impressão de arquivo em PDF!");
                }//if verssao
            }else {
                msg.startMsg("Não há dados de consulta para gerar arquivo !");
            }//if empresa
        } else if (v.getId( ) == R.id.fltBtnInfo)
        {
            startVibrat ( 90 );
            Intent it = new Intent( this,ActInfo.class );
            startActivity( it );
        }else if (v.getId( ) == R.id.fltBtnSair)
        {
            startVibrat ( 90 );
            finish();
        }
    }
    // dialog
    private void alertPrint (final CnpjEmpresa emp ) {

        AlertDialog.Builder alert = new AlertDialog.Builder ( this );
        alert.setIcon ( R.drawable.ic_print_orege_24dp);
        alert.setTitle ( "Imprimir Consulta em arquivo" );
        alert.setMessage ( "Deseja gerar um arquivo em pdf com resultado da sua consulta ?" );

        alert.setPositiveButton ( "Sim", new DialogInterface.OnClickListener ( ) {
            @Override
            public void onClick ( DialogInterface dialog, int which )
            {
                createPDF = new CreatePDF( emp  );
                try {

                    createPDF.gerar();
                    clearComponet();
                    msg.setMsg();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } );

        alert.setNegativeButton ( "Não", new DialogInterface.OnClickListener ( ) {
            @Override
            public void onClick ( DialogInterface dialog, int which ) { }
        } );
        AlertDialog dialog = alert.create ( );
        dialog.show ( );
    }

    private void clearComponet()
    {
        edtCNPJ.setText("");
        cnpjEmpresas = new ArrayList< CnpjEmpresa > ( );
        empresaAdapter = new CnpjEmpresaAdapter ( this, cnpjEmpresas );
        lvCNPJ.setAdapter ( empresaAdapter );
    }
    //oculta teclado
    private void hideKeyboard( View v )
    {
        InputMethodManager imm = (InputMethodManager) getApplicationContext()
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
    //Metudo que ativa vibração
    private void startVibrat ( long tempo ) {
        // cria um obj atvib que recebe seu valor de context
        Vibrator atvib = ( Vibrator ) getSystemService ( Context.VIBRATOR_SERVICE );
        atvib.vibrate ( tempo );
    }
}