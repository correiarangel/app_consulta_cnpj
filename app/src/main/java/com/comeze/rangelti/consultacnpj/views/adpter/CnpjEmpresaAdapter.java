package com.comeze.rangelti.consultacnpj.views.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.comeze.rangelti.consultacnpj.R;
import com.comeze.rangelti.consultacnpj.views.model.CnpjEmpresa;

import java.util.List;

public class CnpjEmpresaAdapter extends BaseAdapter {
	
	private Context context;
	private List< CnpjEmpresa > lista;
	
	public CnpjEmpresaAdapter ( Context context, List< CnpjEmpresa > lista ) {
		this.context = context;
		this.lista = lista;
	}
	
	public int getCount ( ) {
		return lista.size ( );
	}
	
	public Object getItem ( int position ) {
		return lista.get ( position );
	}
	
	public long getItemId ( int position ) {
		return position;
	}
	
	public View getView ( int position, View convertView, ViewGroup parent ) {
		CnpjEmpresa cnpj = lista.get ( position );
				
		LayoutInflater inflater = ( LayoutInflater ) context.getSystemService ( Context.LAYOUT_INFLATER_SERVICE );
		View view = inflater.inflate ( R.layout.act_retorn_cnpj, null );
		
		String razaSocial = cnpj.getNome ( );
		String nomeFantasia = cnpj.getFantasia ( );
		String documento = cnpj.getCnpj ( );
		String dataAbertura = cnpj.getAbertura ( );
		String tipo = cnpj.getTipo ( );
		String atividadePrincipal = cnpj.getAtividade_principal ( );
		String naturezaJuridica = cnpj.getNatureza_juridica ( );
		String logradouro = cnpj.getLogradouro ( );
		String num = cnpj.getNumero ( );
		String complemento = cnpj.getComplemento ( );
		String bairro = cnpj.getBairro ( );
		String cep = cnpj.getCep ( );
		String cidade = cnpj.getMunicipio ( );
		String uf = cnpj.getUf ( );
		String email = cnpj.getEmail ( ) == null? "nulo" : cnpj.getEmail ( ) ;
		String fone = cnpj.getTelefone ( );
		String situacao = cnpj.getSituacao ( );
		String situacaoEspecial = cnpj.getCapital_social ( ) == null? "nulo": cnpj.getCapital_social ( );
		String capitalSocial = cnpj.getData_situacao_especial ( );
		String quadroSocios = cnpj.getQsa ( ) == null ? "nulo" : cnpj.getQsa ( );
		String ultimaAtualização = cnpj.getUltima_atualizacao ( );
		String status2 = cnpj.getStatus ( ) == null ? "nulo" : cnpj.getStatus ( );
		//b = (a > 0) ? 1 : 2;
		
		TextView txtRazao = view.findViewById ( R.id.txtRazao );
		txtRazao.setText ( String.format ( "Razão Social: %s", razaSocial ) );
		
		TextView txtFantasia = view.findViewById ( R.id.txtFantasia );
		txtFantasia.setText ( String.format ( "Nome Fantasia: %s", nomeFantasia ) );
		
		TextView txtCnpj = view.findViewById ( R.id.txtCnpj );
		txtCnpj.setText ( String.format ( "CNPJ: %s", documento ) );
		
		TextView txtDataAbetura = view.findViewById ( R.id.txtDataAbetura );
		txtDataAbetura.setText ( String.format ( "Data Abertura: %s", dataAbertura ) );
		
		TextView txtTipo = view.findViewById ( R.id.txtTipo );
		txtTipo.setText ( String.format ( "Tipo: %s", tipo ) );
		
		TextView txtAtividadePrincipal = view.findViewById ( R.id.txtAtividadePrincipal );
		txtAtividadePrincipal.setText ( String.format ( "Atividade Principal: %s", atividadePrincipal ) );
		
		TextView txtNaturezaJuridica = view.findViewById ( R.id.txtNaturezaJuridica );
		txtNaturezaJuridica.setText ( String.format ( "Natureza Jurídica: %s", naturezaJuridica ) );
		
		TextView txtLongradouro = view.findViewById ( R.id.txtLongradouro );
		txtLongradouro.setText ( String.format ( "End.: %s", logradouro ) );
		
		TextView txtNumero = view.findViewById ( R.id.txtNumero );
		txtNumero.setText ( String.format ( "Número: %s", num ) );
		
		TextView txtComplemento = view.findViewById ( R.id.txtComplemento );
		txtComplemento.setText ( String.format ( "Complemento: %s", complemento ) );
		
		TextView txtBairro = view.findViewById ( R.id.txtBairro );
		txtBairro.setText ( String.format ( "Data abetura: %s", bairro ) );
		
		TextView txtCep = view.findViewById ( R.id.txtCep );
		txtCep.setText ( String.format ( "CEP: %s", cep ) );
		
		TextView txtMunicipio = view.findViewById ( R.id.txtMunicipio );
		txtMunicipio.setText ( String.format ( "Município : %s", cidade ) );
		
		TextView txtEstado = view.findViewById ( R.id.txtEstado );
		txtEstado.setText ( String.format ( "Estado: %s", uf ) );
		
		if ( email != "nulo" ) {
			TextView txtEmail = view.findViewById ( R.id.txtEmail );
			txtEmail.setText ( String.format ( "E-mail: %s", email ) );
		}
		
		TextView txtFone = view.findViewById ( R.id.txtFone );
		txtFone.setText ( String.format ( "Fone: %s", fone ) );
		
		TextView txtSituação = view.findViewById ( R.id.txtSituação );
		txtSituação.setText ( String.format ( "Situação: %s", situacao ) );
		
		if(situacaoEspecial != "nulo") {
			TextView txtDataSituacao = view.findViewById ( R.id.txtDataSituacao );
			txtDataSituacao.setText ( String.format ( "Data Situação: %s", situacaoEspecial ) );
		}
		
		TextView txtCapitalSocial = view.findViewById ( R.id.txtCapitalSocial );
		txtCapitalSocial.setText ( String.format ( "Capital Social: %s", capitalSocial ) );
		
		if(quadroSocios != "nulo" ) {
			TextView txtQuadroSocios = view.findViewById ( R.id.txtQuadroSocios );
			txtQuadroSocios.setText ( String.format ( "Quadro Sócios: %s", quadroSocios ) );
		}
		
		TextView ultimaAtualisacao = view.findViewById ( R.id.txtUltimaAtualisacao );
		ultimaAtualisacao.setText ( String.format ( "Atualizado em: %s", ultimaAtualização ) );
		
		if ( status2 != "nulo" )  {
			TextView status = view.findViewById ( R.id.txtStatus );
			status.setVisibility ( View.INVISIBLE );
			status.setText ( String.format ( "Status: %s", status2 ) );
		}
		
		return view;
	}
}
