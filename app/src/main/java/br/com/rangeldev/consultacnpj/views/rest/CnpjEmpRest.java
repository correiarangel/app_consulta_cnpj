package br.com.rangeldev.consultacnpj.views.rest;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.ListView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import br.com.rangeldev.consultacnpj.views.adpter.CnpjEmpresaAdapter;
import br.com.rangeldev.consultacnpj.views.custom.MsgStatus;
import br.com.rangeldev.consultacnpj.views.model.CnpjEmpresa;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CnpjEmpRest {

	private String url;
	private RequestQueue queue;
	private Context context;
	private ListView lv;
	private CnpjEmpresaAdapter pla;
	private ProgressDialog pDialog;
	private CnpjEmpresa empesa;
	private MsgStatus msg;

	public CnpjEmpRest ( Context context, ListView lv ) {
		this.context = context;
		queue = Volley.newRequestQueue ( this.context );
		this.lv = lv;
		msg = new MsgStatus( context );
	}
	
	public void listCnpj ( final String cnpj ) {
		
		pDialog = new ProgressDialog ( getContext ( ) );
		pDialog.setMessage ( "Carregando dados..." );
		pDialog.show ( );
		
		url = "http://www.receitaws.com.br/v1/cnpj/" + cnpj;
		// prepare the Request
		final JsonObjectRequest getRequest = new JsonObjectRequest ( Request.Method.GET, url, null,

				new Response.Listener< JSONObject > ( ) {
					@Override
					public void onResponse ( JSONObject response ) {
						// display response
						ArrayList< CnpjEmpresa > cnpjEmpresas = new ArrayList<> ( );
						
						try {
							
							for ( int i = 0; i < response.length ( ); i++ ) {
								
								String cnpj = response.getString ( "cnpj" );
								String nome = response.getString ( "nome" );
								String status = response.getString ( "status" );
								String boliing = null;//response.getString("boliing");
								String ultima_atualizacao = response.getString ( "ultima_atualizacao" );
								String tipo = response.getString ( "tipo" );
								String abertura = response.getString ( "abertura" );
								String fantasia = response.getString ( "fantasia" );
								String atividade_principal = response.getString ( "atividade_principal" );
								String atividades_secundarias = response.getString ( "atividades_secundarias" );
								String natureza_juridica = response.getString ( "natureza_juridica" );
								String logradouro = response.getString ( "logradouro" );
								String numero = response.getString ( "numero" );
								String complemento = response.getString ( "complemento" );
								String cep = response.getString ( "cep" );
								String bairro = response.getString ( "bairro" );
								String municipio = response.getString ( "municipio" );
								String uf = response.getString ( "uf" );
								String porte = response.getString ( "porte" );
								String email = response.getString ( "email" );
								String telefone = response.getString ( "telefone" );
								String efr = response.getString ( "efr" );
								String situacao = response.getString ( "situacao" );
								String motivo_situacao = response.getString ( "motivo_situacao" );
								String situacao_especial = response.getString ( "situacao_especial" );
								String data_situacao_especial = response.getString ( "data_situacao_especial" );
								String capital_social = response.getString ( "capital_social" );
								String qsa = response.getString ( "qsa" ); //Quadro de Sócios e Administradores.
								String extra = response.getString ( "extra" );

								empesa = new CnpjEmpresa ( boliing, status, cnpj, ultima_atualizacao
										, tipo, abertura, nome, fantasia, atividade_principal
										, atividades_secundarias, natureza_juridica, logradouro
										, numero, complemento, cep, bairro, municipio, uf, porte
										, email, telefone, efr, situacao, motivo_situacao
										, data_situacao_especial, capital_social, qsa
										, situacao_especial, extra );
								
							}
							cnpjEmpresas.add ( empesa );

						} catch ( JSONException e ) {
							e.printStackTrace ( );

							String statusCode = e.getMessage();

							if (statusCode.equals("No value for cnpj")){
								msg.startMsg("CNPJ infomado é invalido !");
							}

						}
						setPla ( new CnpjEmpresaAdapter ( getContext ( ), cnpjEmpresas ) );
						getLv ( ).setAdapter ( getPla ( ) );
						//fecha dialog
						getpDialog ( ).dismiss ( );
					}
				},
				new Response.ErrorListener ( ) {
					@Override
					public void onErrorResponse ( VolleyError error ) {

						String erroConexao = "com.android.volley.NoConnectionError: " +
								"java.net.UnknownHostException: Unable to resolve host " +
								"\"www.receitaws.com.br\": No address associated with hostname";
						String erroPermissao = "com.android.volley.VolleyError: " +
								"java.lang.SecurityException: " +
								"Permission denied (missing INTERNET permission?)";

						String errorRetorn = error.toString ( );

						if (errorRetorn.equals("com.android.volley.ClientError")) {

							msg.startMsg("Serviço indisponivel :[");
							msg.startMsg("Se você já fez três consultas aguarde um minuto !");
							msg.startMsg("Antes de tentar novamente !");
							msg.startMsg("Toque na tela para parar o processo !");

						} else if(errorRetorn.equals(erroConexao)) {

							msg.startMsg("Internet indisponivel :[");
							msg.startMsg("Virifique sua conexão !");
							msg.startMsg("Toque na tela para parar o processo !");

						} else if(errorRetorn.equals("com.android.volley.ServerError")) {

							msg.startMsg("Serviço falhou :[");
							msg.startMsg("Rota para Servidor não encontrada!");
							msg.startMsg("Tente novamente em alguns minutos !");
							msg.startMsg("Toque na tela para parar o processo !");

						} else if(errorRetorn.equals(erroPermissao)) {

							msg.startMsg("Não há permissão para conexão com Internet :[");
							msg.startMsg("Se o erro continuar remoava e reinstale o App!");
							msg.startMsg("Toque na tela para parar o processo !");

						} else if (error instanceof NetworkError) {
						} else if (error instanceof ServerError) {
						} else if (error instanceof ParseError) {
						} else if (error instanceof NoConnectionError) {
						} else if (error instanceof TimeoutError)
						{
							msg.startMsg("Oops.O tempo foi excedido. Timeout error :[");

						}
					}
				}
		);

		getRequest.setRetryPolicy(new DefaultRetryPolicy(
				15000,
				DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
				DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
		// add it to the RequestQueue
		queue.add ( getRequest );
	}

	public Context getContext ( ) { return context;  }

	public void setContext ( Context context ) { this.context = context; }

	public ListView getLv ( ) { return lv; }
	
	public void setLv ( ListView lv ) { this.lv = lv; }
	
	public CnpjEmpresaAdapter getPla ( ) { return pla; }
	
	public void setPla ( CnpjEmpresaAdapter pla ) { this.pla = pla; }
	
	public ProgressDialog getpDialog ( ) { return pDialog; }
	
}