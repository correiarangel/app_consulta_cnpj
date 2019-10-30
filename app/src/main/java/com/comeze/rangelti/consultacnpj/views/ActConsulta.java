package com.comeze.rangelti.consultacnpj.views;


import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.print.PrintDocumentAdapter;
import android.print.PrintManager;

import android.print.pdf.PrintedPdfDocument;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import com.comeze.rangelti.consultacnpj.R;
import com.comeze.rangelti.consultacnpj.views.adpter.CnpjEmpresaAdapter;
import com.comeze.rangelti.consultacnpj.views.custom.PrintPDF;
import com.comeze.rangelti.consultacnpj.views.model.CnpjEmpresa;
import com.comeze.rangelti.consultacnpj.views.rest.CnpjEmpRest;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;



public class ActConsulta extends AppCompatActivity {

	private EditText edtCNPJ;
	private Button btnPesquisar;
	private CnpjEmpresa cnpj;
	private List< CnpjEmpresa > cnpjEmpresas;
	private FloatingActionButton fltBtnPrint;
	private PrintDocumentAdapter pda;
	private ListView lvCNPJ;

	private CnpjEmpRest cnpjEmpRest;
	private CnpjEmpresaAdapter empresaAdapter;

	private AdapterView.OnItemClickListener selecionarCnpj = new AdapterView.OnItemClickListener ( ) {

		public void onItemClick ( AdapterView< ? > arg0, View arg1, int pos, long id ) {

			cnpj = ( CnpjEmpresa ) cnpjEmpRest.getPla ( ).getItem ( pos );
			startMsg("PDF:"+cnpj);
			PrintPDF printPDF = new PrintPDF ();
			try {
				printPDF.geralPDF (cnpj);

			} catch ( Exception e ) {
				e.printStackTrace ( );
			}

		}

	};

	@Override
	protected void onCreate ( Bundle savedInstanceState ) {
		super.onCreate ( savedInstanceState );
		setContentView ( R.layout.act_consulta );

		//ActionBar bar = getSupportActionBar ( );
		// bar.setBackgroundDrawable ( new ColorDrawable( Color.parseColor ( "#575a5e" ) ) );
		//bar.setTitle ( "Consulte CNPJ" );

		startComponent ( );
		lvCNPJ.setOnItemClickListener ( selecionarCnpj );
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

		btnPesquisar.setOnClickListener ( new View.OnClickListener ( ) {
			@Override
			public void onClick ( View v ) {
				startVibrat ( 90 );
				getCnpj ( );
			}
		} );

		fltBtnPrint.setOnClickListener ( new View.OnClickListener ( ) {
			@Override
			public void onClick ( View v ) {

				if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ) {
					startMsg ( lvCNPJ.toString() );
					createPdf(lvCNPJ);


				} else {
					startMsg ( "Dispositivo incompatível com impressão!" );
					//	startMsg ( "Seu aparelho possui vesão inferior a KITKAT" );
				}
			}
		} );
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
	private void startMsg ( String msg ) {
		Toast.makeText ( getApplicationContext ( ), msg, Toast.LENGTH_LONG ).show ( );
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

	//metudo gera pdf
	@RequiresApi(api = Build.VERSION_CODES.KITKAT)
	private void createPdf(ListView listView){
		// criando o documento novo

		PdfDocument document = new PdfDocument();

		PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();

		PdfDocument.Page page = document.startPage(pageInfo);
		Canvas canvas = page.getCanvas();
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		canvas.drawCircle(50, 50, 30, paint);
		paint.setColor(Color.BLACK);
		canvas.drawText(listView.toString(), 80, 50, paint);


		document.finishPage(page);

		//Criando uma segunda página
		pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 2).create();
		page = document.startPage(pageInfo);
		canvas = page.getCanvas();
		paint = new Paint();
		paint.setColor(Color.BLUE);
		canvas.drawCircle(100, 100, 100, paint);
		document.finishPage(page);

		String directory_path = Environment.getExternalStorageDirectory().getPath() + "/";
		File file = new File(directory_path);
		if (!file.exists()) {
			file.mkdirs();
		}

		String targetPdf = directory_path+"ColsultaCNPJ.pdf";
		File filePath = new File(targetPdf);
		try {
			document.writeTo(new FileOutputStream(filePath));
			startMsg("Salvo com sucesso");
		} catch (IOException e) {
			Log.e("main", "error "+e.toString());
			startMsg( "Não funcionou: " + e.toString());
		}

		document.close();
	}


}
