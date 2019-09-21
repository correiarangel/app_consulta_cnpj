package com.comeze.rangelti.consultacnpj.views.rest;


import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.comeze.rangelti.consultacnpj.views.adpter.CnpjEmpresaAdapter;
import com.comeze.rangelti.consultacnpj.views.model.CnpjEmpresa;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class CnpjEmpRest {


    private String url ;
    private RequestQueue queue;
    private Context context;
    private ListView lv;
    private CnpjEmpresaAdapter pla;
    private ProgressDialog pDialog;
    private CnpjEmpresa empesa ;


    public CnpjEmpRest(Context context, ListView lv) {
        this.context = context;
        queue = Volley.newRequestQueue(this.context );
        this.lv= lv;

    }

    public void listCnpj(final String cnpj) {

        pDialog = new ProgressDialog(getContext());
        pDialog.setMessage("Carregando dados...");
        pDialog.show();

        url="http://www.receitaws.com.br/v1/cnpj/"+cnpj;


        // prepare the Request
        JsonObjectRequest getRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>()
                {
                    @Override
                    public void onResponse(JSONObject response) {

                        // display response
                        ArrayList< CnpjEmpresa > cnpjEmpresas = new ArrayList<>();

                        try {

                            for (int i = 0; i < response.length(); i++) {


                                String cnpj = response.getString("cnpj");
                                String nome = response.getString("nome");
                                String status = response.getString("status");
                                String  boliing = null;//response.getString("boliing");
                                String ultima_atualizacao = response.getString("ultima_atualizacao");
                                String tipo = response.getString("tipo");
                                String abertura = response.getString("abertura");
                                String fantasia = response.getString("fantasia");
                                String atividade_principal = response.getString("atividade_principal");
                                String atividades_secundarias = response.getString("atividades_secundarias");
                                String natureza_juridica = response.getString("natureza_juridica");
                                String logradouro = response.getString("logradouro");
                                String numero = response.getString("numero");
                                String complemento = response.getString("complemento");
                                String cep = response.getString("cep");
                                String bairro = response.getString("bairro");
                                String municipio = response.getString("municipio");
                                String uf = response.getString("uf");
                                String porte = response.getString("porte");
                                String email = response.getString("email");
                                String telefone = response.getString("telefone");
                                String efr = response.getString("efr");
                                String situacao = response.getString("situacao");
                                String motivo_situacao = response.getString("motivo_situacao");
                                String situacao_especial = response.getString("situacao_especial");
                                String data_situacao_especial = response.getString("data_situacao_especial");
                                String capital_social = response.getString("capital_social");
                                String qsa = response.getString("qsa"); //Quadro de Sócios e Administradores.
                                String extra = response.getString("extra");


                                empesa = new CnpjEmpresa ( boliing ,status ,cnpj ,ultima_atualizacao
                                        ,tipo ,abertura ,nome ,fantasia ,atividade_principal
                                        ,atividades_secundarias ,natureza_juridica ,logradouro
                                        ,numero ,complemento ,cep ,bairro ,municipio ,uf ,porte
                                        ,email ,telefone ,efr ,situacao ,motivo_situacao
                                        ,data_situacao_especial ,capital_social ,qsa
                                        ,situacao_especial ,extra);

                            }

                            cnpjEmpresas.add(empesa);
                        }catch (JSONException e) {
                            e.printStackTrace();
                        }


                        setPla(new CnpjEmpresaAdapter(getContext(), cnpjEmpresas ));
                        getLv().setAdapter(getPla());
                        //fecha dialog
                        getpDialog().dismiss();
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("Error.Response", error.toString());
                    }
                }
        );

        // add it to the RequestQueue
        queue.add(getRequest);

    }



    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ListView getLv() {
        return lv;
    }

    public void setLv(ListView lv) {
        this.lv = lv;
    }

    public void setPla(CnpjEmpresaAdapter pla) {
        this.pla = pla;
    }

    public CnpjEmpresaAdapter getPla() {
        return pla;
    }

    public ProgressDialog getpDialog() {
        return pDialog;
    }

}



