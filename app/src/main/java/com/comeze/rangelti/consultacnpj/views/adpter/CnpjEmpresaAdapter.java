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
		
		
		TextView txtRazao = ( TextView ) view.findViewById ( R.id.txtRazao );
		txtRazao.setText ( "Razão Social: " + cnpj.getNome ( ) );
		
		TextView txtFantasia = ( TextView ) view.findViewById ( R.id.txtFantasia );
		txtFantasia.setText ( "Fantasia: " + cnpj.getFantasia ( ) );
		
		TextView txtCnpj = ( TextView ) view.findViewById ( R.id.txtCnpj );
		txtCnpj.setText ( "CNPJ: " + cnpj.getCnpj ( ) );
		
		TextView txtDataAbetura = ( TextView ) view.findViewById ( R.id.txtDataAbetura );
		txtDataAbetura.setText ( "Data abetura : " + cnpj.getAbertura ( ) );
		
		
		TextView txtTipo = ( TextView ) view.findViewById ( R.id.txtTipo );
		txtTipo.setText ( "Tipo : " + cnpj.getTipo ( ) );
		
		TextView txtAtividadePrincipal = ( TextView ) view.findViewById ( R.id.txtAtividadePrincipal );
		txtAtividadePrincipal.setText ( "Atividade Principal: " + cnpj.getAtividade_principal ( ) );
		
		TextView txtNaturezaJuridica = ( TextView ) view.findViewById ( R.id.txtNaturezaJuridica );
		txtNaturezaJuridica.setText ( "Natureza Juridica: " + cnpj.getNatureza_juridica ( ) );
		
		TextView txtLongradouro = ( TextView ) view.findViewById ( R.id.txtLongradouro );
		txtLongradouro.setText ( "End. : " + cnpj.getStatus ( ) );
		
		TextView txtNumero = ( TextView ) view.findViewById ( R.id.txtNumero );
		txtNumero.setText ( "Numero : " + cnpj.getNumero ( ) );
		
		TextView txtComplemento = ( TextView ) view.findViewById ( R.id.txtComplemento );
		txtComplemento.setText ( "Complemento : " + cnpj.getComplemento ( ) );
		
		TextView txtBairro = ( TextView ) view.findViewById ( R.id.txtBairro );
		txtBairro.setText ( "Data abetura : " + cnpj.getBairro ( ) );
		
		TextView txtCep = ( TextView ) view.findViewById ( R.id.txtCep );
		txtCep.setText ( "CEP : " + cnpj.getCep ( ) );
		
		TextView txtMunicipio = ( TextView ) view.findViewById ( R.id.txtMunicipio );
		txtMunicipio.setText ( "Municipio : " + cnpj.getMunicipio ( ) );
		
		TextView txtEstado = ( TextView ) view.findViewById ( R.id.txtEstado );
		txtEstado.setText ( "Estado: " + cnpj.getUf ( ) );
		
		TextView txtEmail = ( TextView ) view.findViewById ( R.id.txtEmail );
		txtEmail.setText ( "E-mail: " + cnpj.getEmail ( ) );
		
		TextView txtFone = ( TextView ) view.findViewById ( R.id.txtFone );
		txtFone.setText ( "Fone. : " + cnpj.getTelefone ( ) );
		
		TextView txtSituação = ( TextView ) view.findViewById ( R.id.txtSituação );
		txtSituação.setText ( "Situação : " + cnpj.getSituacao ( ) );
		
		TextView txtDataSituacao = ( TextView ) view.findViewById ( R.id.txtDataSituacao );
		txtDataSituacao.setText ( "Data Situação : " + cnpj.getData_situacao_especial ( ) );
		
		TextView txtCapitalSocial = ( TextView ) view.findViewById ( R.id.txtCapitalSocial );
		txtCapitalSocial.setText ( "Capital Social : " + cnpj.getCapital_social ( ) );
		
		TextView txtQuadroSocios = ( TextView ) view.findViewById ( R.id.txtQuadroSocios );
		txtQuadroSocios.setText ( "Quadro Socios : " + cnpj.getQsa ( ) );
		
		TextView ultimaAtualisacao = ( TextView ) view.findViewById ( R.id.txtUltimaAtualisacao );
		ultimaAtualisacao.setText ( "Atualisado em :" + cnpj.getUltima_atualizacao ( ) );
		
		TextView status = ( TextView ) view.findViewById ( R.id.txtStatus );
		status.setText ( "Status : " + cnpj.getStatus ( ) );
		
		
		return view;
	}
}
