package br.com.rangeldev.consultacnpj.views.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import br.com.rangeldev.consultacnpj.R;
import br.com.rangeldev.consultacnpj.views.model.CnpjEmpresa;

import java.util.List;

public class CnpjEmpresaAdapter extends BaseAdapter {
	
	private Context context;
	private List<CnpjEmpresa> lista;
	
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
		String documentoCnpj = cnpj.getCnpj ( );
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
		String email = cnpj.getEmail ( );
		String fone = cnpj.getTelefone ( );
		String situacao = cnpj.getSituacao ( );
		String dataSituacao = cnpj.getData_situacao_especial();
		String capitalSocial = cnpj.getQsa ( );
		String quadroSocios = cnpj.getCapital_social ( );
		String ultimaAtualizacao = cnpj.getUltima_atualizacao ( );
		String status2 = cnpj.getStatus ( );
		//b = (a > 0) ? 1 : 2;
		
		TextView txtRazao = view.findViewById ( R.id.txtRazao );
		txtRazao.setText ( String.format ( "Razão Social: %s", razaSocial ) );
		
		TextView txtFantasia = view.findViewById ( R.id.txtFantasia );
		txtFantasia.setText ( String.format ( "Nome Fantasia: %s", nomeFantasia ) );
		
		TextView txtCnpj = view.findViewById ( R.id.txtCnpj );
		txtCnpj.setText ( String.format ( "CNPJ: %s", documentoCnpj ) );
		
		TextView txtDataAbetura = view.findViewById ( R.id.txtDataAbetura );
		txtDataAbetura.setText ( String.format ( "Data Abertura: %s", dataAbertura ) );
		
		TextView txtTipo = view.findViewById ( R.id.txtTipo );
		txtTipo.setText ( String.format ( "Tipo: %s", tipo ) );
		
		TextView txtAtividadePrincipal = view.findViewById ( R.id.txtAtividadePrincipal );
		if ( atividadePrincipal.equals ( "" ) || ( atividadePrincipal == null ) || atividadePrincipal.equals ( "[]" ) ) {
			txtAtividadePrincipal.setText("Atividade Principal: não informado");
		} else {
			txtAtividadePrincipal.setText ( String.format ( "Atividade Principal%s ", atividadePrincipal )
					.replace ( "[{", "" )
					.replace("text", "")
					.replace("::", ":")
					.replace(": :", ":")
					.replace("code", " código")
					.replace("}]","")
					.replaceAll("\"", "")
			);
		}
		TextView txtNaturezaJuridica = view.findViewById ( R.id.txtNaturezaJuridica );
		txtNaturezaJuridica.setText ( String.format ( "Natureza Jurídica: %s", naturezaJuridica ) );
		
		TextView txtLongradouro = view.findViewById ( R.id.txtLongradouro );
		txtLongradouro.setText ( String.format ( "Logradouro %s", logradouro ) );
		
		TextView txtNumero = view.findViewById ( R.id.txtNumero );
		txtNumero.setText ( String.format ( "Número: %s", num ) );
		
		TextView txtComplemento = view.findViewById ( R.id.txtComplemento );
		if ( complemento.equals ( "" ) || ( complemento == null ) || complemento.equals ( "[]" ) ) {
			txtComplemento.setText ( "Complemento: não informado" );
		} else {
			txtComplemento.setText ( String.format ( "Complemento: %s", complemento ) );
		}
		
		TextView txtBairro = view.findViewById ( R.id.txtBairro );
		txtBairro.setText ( String.format ( "Data abetura: %s", bairro ) );
		
		TextView txtCep = view.findViewById ( R.id.txtCep );
		txtCep.setText ( String.format ( "CEP: %s", cep ) );
		
		TextView txtMunicipio = view.findViewById ( R.id.txtMunicipio );
		txtMunicipio.setText ( String.format ( "Município : %s", cidade ) );
		
		TextView txtEstado = view.findViewById ( R.id.txtEstado );
		txtEstado.setText ( String.format ( "Estado: %s", uf ) );
		
		TextView txtEmail = view.findViewById ( R.id.txtEmail );
		if ( email.equals ( "" ) || ( email == null ) || email.equals ( "[]" ) ) {
			txtEmail.setText ( "Email: não informado" );
		} else {
			txtEmail.setText ( String.format ( "E-mail: %s", email ) );
		}
		
		TextView txtFone = view.findViewById ( R.id.txtFone );
		if ( fone.equals ( "" ) || ( fone == null ) || fone.equals ( "[]" ) ) {
			txtFone.setText ( "Fone: não informado" );
		} else {
			txtFone.setText ( String.format ( "Fone: %s", fone ) );
		}

		TextView txtSituacao = view.findViewById ( R.id.txtSituacao );
		if ( situacao.equals ( "" ) || ( situacao == null ) || situacao.equals ( "[]" ) ) {
			txtSituacao.setText ( "Situação: não informado" );
		} else {
			txtSituacao.setText ( String.format ( "Situação: %s", situacao ) );
		}
		
		TextView txtDataSituacao = view.findViewById ( R.id.txtDataSituacao );
		if ( dataSituacao.equals ( "" ) || ( dataSituacao == null ) || dataSituacao.equals ( "[]" ) ) {
			txtDataSituacao.setText ( "Situacao: não informado" );
		} else {
			txtDataSituacao.setText ( String.format ( "Situacao: %s", dataSituacao ) );
		}
		
		TextView txtCapitalSocial = view.findViewById ( R.id.txtCapital_Social );
		if ( capitalSocial.equals ( "" ) || ( capitalSocial == null ) || capitalSocial.equals ( "[]" )){
			txtCapitalSocial.setText("Capital Social:não informado");
		}else {
			txtCapitalSocial.setText(String.format("Capital Social: %s", capitalSocial));
		}

		TextView txtQuadroSocios = view.findViewById ( R.id.txtQuadroSocios );
		if ( quadroSocios.equals ( "" ) || ( quadroSocios == null ) || quadroSocios.equals ( "[]" ) ) {
			txtQuadroSocios.setText ( "Quadro Sócios: não informado" );
		} else {
			txtQuadroSocios.setText ( String.format ( "Quadro Sócios: %s", quadroSocios ) );
		}
		
		TextView ultAtualizacao = view.findViewById ( R.id.txtUltimaAtualisacao );
		if ( ultAtualizacao.equals ( "" ) || ( ultAtualizacao == null ) || ultAtualizacao.equals ( "[]" ) ) {
			ultAtualizacao.setText ( "Atualizado em: não informado" );
		} else {
			ultAtualizacao.setText ( String.format ( "Atualizado em: %s", ultimaAtualizacao ).substring ( 0, 25 ) );
		}
		
		if ( status2 != "nulo" ) {
			TextView status = view.findViewById ( R.id.txtStatus );
			status.setVisibility ( View.INVISIBLE );
			status.setText ( String.format ( "Status: %s", status2 ) );
		}
		
		return view;
	}

	public void onFinish() {
	}
}
