package com.comeze.rangelti.consultacnpj.views;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;


import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import com.comeze.rangelti.consultacnpj.R;
import com.comeze.rangelti.consultacnpj.views.adpter.CnpjEmpresaAdapter;
import com.comeze.rangelti.consultacnpj.views.custom.GerarPDF;
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
	private ListView lvCNPJ;
	GerarPDF gerarPDF ;

	private CnpjEmpRest cnpjEmpRest;
	private CnpjEmpresaAdapter empresaAdapter;

	private AdapterView.OnItemClickListener selecionarCnpj = new AdapterView.OnItemClickListener ( ) {

		public void onItemClick ( AdapterView< ? > arg0, View arg1, int pos, long id ) {

			empresa = ( CnpjEmpresa ) cnpjEmpRest.getPla ( ).getItem ( pos );
			//method decision
			alertPrint( empresa );

			try {


			} catch ( Exception e ) {
				e.printStackTrace ( );
			}

		}

	};

	@Override
	protected void onCreate ( Bundle savedInstanceState ) {
		super.onCreate ( savedInstanceState );
		setContentView ( R.layout.act_consulta );
/*
		ActionBar bar = getSupportActionBar ( );
		bar.setBackgroundDrawable ( new ColorDrawable( Color.parseColor ( "#575a5e" ) ) );
		bar.setTitle ( "Consulte CNPJ" );

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
*/
		startComponent ( );
		lvCNPJ.setOnItemClickListener ( selecionarCnpj );
        //call method read write
		testePermissoes();
	}


	public void startComponent ( ) {

		fltBtnPrint = findViewById ( R.id.fltBtnPrint );
		edtCNPJ = findViewById ( R.id.edtCNPJ );
		btnPesquisar = findViewById ( R.id.btnPesquisar );
		lvCNPJ = findViewById ( R.id.lvCNPJ );

		cnpjEmpresas = new ArrayList< CnpjEmpresa > ( );
		empresaAdapter = new CnpjEmpresaAdapter ( this, cnpjEmpresas );
		lvCNPJ.setAdapter ( empresaAdapter );

		cnpjEmpRest = new CnpjEmpRest ( this, lvCNPJ );

		btnPesquisar.setOnClickListener(this);
		fltBtnPrint.setOnClickListener(this);

	}
	//metudo que sobreescreve fontes coistomiza fonte
	@Override
	protected void attachBaseContext ( Context newBase ) {
		super.attachBaseContext ( CalligraphyContextWrapper.wrap ( newBase ) );
	}

	//Metudo que ativa vibração
	private void startVibrat ( long tempo ) {
		// cria um obj atvib que recebe seu valor de context
		Vibrator atvib = ( Vibrator ) getSystemService ( Context.VIBRATOR_SERVICE );
		atvib.vibrate ( tempo );
	}

	//msg
	public void startMsg(String message) {

		int duration = Toast.LENGTH_LONG;
		Toast toast  = Toast.makeText(getApplicationContext(), message, duration);
		toast.show();

	}

	//trata string
	private String beautifyCNPJ ( String doc ) {
		doc = doc.replace ( ".", "" );
		doc = doc.replace ( "/", "" );
		doc = doc.replace ( "-", "" );
		//startMsg ( String.format ( "%s", doc ) );
		return doc;
	}

	//captura cnpj e envia como parametro
	// para cnpjEmpRest
	private void getCnpj ( ) {

		String cnpj = beautifyCNPJ ( edtCNPJ.getText ( ).toString ( ) );

		if ( cnpj.equals ( "" ) ) {
			startMsg ( "Não é possivel fazer busca sem um CNPJ!" );
			startMsg ( "Por favor, digite um CNPJ para consulta." );

		} else {

			try {

				cnpjEmpRest.listCnpj ( cnpj.trim ( ) );

			} catch ( Exception err ) {
				startMsg ( "Ocorreu erro ao busca  CNPJ !..." );
				System.out.println ( "ERRO----------" + err );
			}
		}
	}

	//Menu ------//
	@Override
	public boolean onCreateOptionsMenu ( Menu menu ) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater ( ).inflate ( R.menu.menu_consulta, menu );
		return true;
	}

	@Override
	public boolean onOptionsItemSelected ( MenuItem item ) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId ( );

		//noinspection SimplifiableIfStatement
		if ( id == R.id.action_settings ) {
			return true;
		}

		return super.onOptionsItemSelected ( item );
	}




    // metudos verificam permissão de leitura e gravação em disco
	// Em tempo de execução só funcionou com  checkPermission
	// e requestPermissionAndContinue
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
		//add your further process after giving permission or to download images from remote server.
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

	@Override
	public void onClick ( View v ) {
		if ( v.getId ( ) == R.id.btnPesquisar )
		{
			startVibrat ( 90 );
			getCnpj ( );

		} else if ( v.getId ( ) == R.id.fltBtnPrint )
		{
			empresa = ( CnpjEmpresa ) cnpjEmpRest.getPla ( ).getItem ( 0 );

			if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ) {

				startVibrat ( 90 );
				gerarPDF = new GerarPDF();
				gerarPDF.createPdf(empresa);

			} else {
				startMsg ( "Dispositivo incompatível com impressão!" );
				//	startMsg ( "Seu aparelho possui vesão inferior a KITKAT" );
			}
		}
	}

	// dialog
	private void alertPrint (final CnpjEmpresa emp ) {

		AlertDialog.Builder alert = new AlertDialog.Builder ( this );
		alert.setIcon ( R.drawable.ic_print_orege_24dp );
		alert.setTitle ( "Imprimir Consulta " );
		alert.setMessage ( "Deseja gerar um arquivo em pdf com resultado da sua consulta ?" );


		alert.setPositiveButton ( "Sim", new DialogInterface.OnClickListener ( ) {
			@Override
			public void onClick ( DialogInterface dialog, int which )
			{
				gerarPDF = new GerarPDF();
				gerarPDF.createPdf(emp);
			}
		} );

		alert.setNegativeButton ( "Não", new DialogInterface.OnClickListener ( ) {
			@Override
			public void onClick ( DialogInterface dialog, int which ) {

			}
		} );
		AlertDialog dialog = alert.create ( );
		dialog.show ( );
	}

	public void setMsg(String s){
		if ( s.equals("OK")) {
			startMsg("Arquivo gerado com sucesso !");
		}else {
			startMsg("Falha na geração do arquivo:");
		}
	}

}
