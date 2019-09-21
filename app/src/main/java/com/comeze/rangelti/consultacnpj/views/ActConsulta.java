package com.comeze.rangelti.consultacnpj.views;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.os.Vibrator;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.PrintManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class ActConsulta extends AppCompatActivity {
	
	private EditText edtCNPJ;
	private Button btnPesquisar;
	private CnpjEmpresa cnpj;
	private List< CnpjEmpresa > cnpjEmpresas;
	private FloatingActionButton fltBtnPrint;
	
	private ListView lvCNPJ;
	
	private CnpjEmpRest cnpjEmpRest;
	private CnpjEmpresaAdapter empresaAdapter;
	private AdapterView.OnItemClickListener selecionarCnpj = new AdapterView.OnItemClickListener ( ) {
		
		public void onItemClick ( AdapterView< ? > arg0, View arg1, int pos, long id ) {
			
			cnpj = ( CnpjEmpresa ) cnpjEmpRest.getPla ( ).getItem ( pos );
			PrintPDF printPDF = new PrintPDF ( );
			try {
				printPDF.geralPDF ( cnpj );
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
					startPrint ( );
				} else {
					startMsg ( "Dispositivo incompatível com impressão!" );
				//	startMsg ( "Seu aparelho possui vesão inferior a KITKAT" );
				}
			}
		} );
	}
	
	@RequiresApi ( api = Build.VERSION_CODES.KITKAT )
	private void startPrint ( ) {
		
		
		startMsg ( "result-" + cnpj.toString ( ) );
		
		PrintDocumentAdapter pda = new PrintDocumentAdapter ( ) {
			
			@Override
			public void onWrite ( PageRange[] pages, ParcelFileDescriptor destination, CancellationSignal cancellationSignal, WriteResultCallback callback ) {
				InputStream input = null;
				OutputStream output = null;
				
				try {
					String Name_of_file = cnpj.toString ( );
					input = new FileInputStream ( Name_of_file );
					output = new FileOutputStream ( destination.getFileDescriptor ( ) );
					
					byte[] buf = new byte[ 1024 ];
					int bytesRead;
					
					while ( ( bytesRead = input.read ( buf ) ) > 0 ) {
						output.write ( buf, 0, bytesRead );
					}
					
					callback.onWriteFinished ( new PageRange[] { PageRange.ALL_PAGES } );
					
				} catch ( FileNotFoundException error ) {
					//Catch exception
				} catch ( Exception error ) {
					//Catch exception
				} finally {
					try {
						input.close ( );
						output.close ( );
					} catch ( IOException e ) {
						e.printStackTrace ( );
					}
				}
			}
			
			@Override
			public void onLayout ( PrintAttributes oldAttributes,
			                       PrintAttributes newAttributes,
			                       CancellationSignal cancellationSignal,
			                       LayoutResultCallback callback,
			                       Bundle extras ) {
				
				if ( cancellationSignal.isCanceled ( ) ) {
					callback.onLayoutCancelled ( );
					return;
				}
				
				
				PrintDocumentInfo pdi = new PrintDocumentInfo.Builder ( "CNPJ" )
						.setContentType ( PrintDocumentInfo.CONTENT_TYPE_DOCUMENT ).build ( );
				
				callback.onLayoutFinished ( pdi, true );
			}
		};
		PrintManager printManager = ( PrintManager ) this.getSystemService ( Context.PRINT_SERVICE );
		String jobName = this.getString ( R.string.app_name ) + " Document";
		printManager.print ( jobName, pda, null );
		
	}
	
	/*
	 * Para que a nova fonte seja exibida na tela,
	 * precisamos chamar um método na Activity que sobrescreva
	 * o contexto base com um Wrapper da biblioteca.
	 */
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
	
	private void startMsg ( String msg ) {
		Toast.makeText ( getApplicationContext ( ), msg, Toast.LENGTH_LONG ).show ( );
	}
	
	private String beautifyCNPJ ( String doc ) {
		doc = doc.replace ( ".", "" );
		doc = doc.replace ( "/", "" );
		doc = doc.replace ( "-", "" );
		//startMsg ( String.format ( "%s", doc ) );
		return doc;
	}
	
	private void getCnpj ( ) {
		
		String cnpj = beautifyCNPJ ( edtCNPJ.getText ( ).toString ( ) );
		
		if ( cnpj.equals ( "" ) ) {
			startMsg ( "Não é possivel fazer busca sem um CNPJ!" );
			startMsg ( "Por favor, digite um CNPJ para consulta." );
			
		} else {
			
			try {
				
				cnpjEmpRest.listCnpj ( cnpj.trim ( ) );
				
			} catch ( Exception err ) {
				startMsg ( "Ocorreu erro na busca pelo CNPJ." );
				
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
	
	
}
