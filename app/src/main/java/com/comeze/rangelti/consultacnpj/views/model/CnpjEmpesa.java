package com.comeze.rangelti.consultacnpj.views.model;

import java.io.Serializable;


public class CnpjEmpesa implements Serializable {

    private String status;
    // boliing Indica para a requisição como foi registrado a cobrança da consulta.
    private  String boliing;
    private String cnpj;
    private String ultima_atualizacao;
    private String tipo;
    private String abertura;
    private String nome;
    private String fantasia;
    private String  atividade_principal;
    private String atividades_secundarias;
    private String natureza_juridica;
    private String logradouro;
    private String numero;
    private String complemento;
    private String cep;
    private String bairro;
    private String municipio;
    private String uf;
    private String porte;
    private String email;
    private String telefone;
    //efr Ente Federativo Responsável, disponibilizado apenas
    //para CNPJs da administração pública.
    private String efr ;
    private String situacao;
    private String motivo_situacao;
    private String situacao_especial;
    private String data_situacao_especial;
    private String capital_social;
    private String qsa; //Quadro de Sócios e Administradores.
  //  private String qual;
    private String extra;

    public CnpjEmpesa(
            String status,String  boliing, String cnpj,
            String ultima_atualizacao, String tipo, String abertura,
            String nome, String fantasia, String atividade_principal,
            String atividades_secundarias, String natureza_juridica,
            String logradouro, String numero, String complemento, String cep,
            String bairro, String municipio, String uf, String porte, String email,
            String telefone, String efr, String situacao, String motivo_situacao,
            String situacao_especial, String data_situacao_especial, String capital_social,
            String qsa, String  extra )
    {
        this.status = status;
        this.boliing = boliing;
        this.cnpj = cnpj;
        this.ultima_atualizacao = ultima_atualizacao;
        this.tipo = tipo;
        this.abertura = abertura;
        this.nome = nome;
        this.fantasia = fantasia;
        this.atividade_principal = atividade_principal;
        this.atividades_secundarias = atividades_secundarias;
        this.natureza_juridica = natureza_juridica;
        this.logradouro = logradouro;
        this.numero = numero;
        this.complemento = complemento;
        this.cep = cep;
        this.bairro = bairro;
        this.municipio = municipio;
        this.uf = uf;
        this.porte = porte;
        this.email = email;
        this.telefone = telefone;
        this.efr = efr;
        this.situacao = situacao;
        this.motivo_situacao = motivo_situacao;
        this.situacao_especial = situacao_especial;
        this.data_situacao_especial = data_situacao_especial;
        this.capital_social = capital_social;
        this.qsa = qsa;
        //this.qual= qual;
        this.extra = extra;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBoliing() {
        return boliing;
    }

    public void setBoliing(String boliing) {
        this.boliing = boliing;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getUltima_atualizacao() {
        return ultima_atualizacao;
    }

    public void setUltima_atualizacao(String ultima_atualizacao) {
        this.ultima_atualizacao = ultima_atualizacao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getAbertura() {
        return abertura;
    }

    public void setAbertura(String abertura) {
        this.abertura = abertura;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getAtividade_principal() {
        return atividade_principal;
    }

    public void setAtividade_principal(String atividade_principal) {
        this.atividade_principal = atividade_principal;
    }

    public String getAtividades_secundarias() {
        return atividades_secundarias;
    }

    public void setAtividades_secundarias(String atividades_secundarias) {
        this.atividades_secundarias = atividades_secundarias;
    }

    public String getNatureza_juridica() {
        return natureza_juridica;
    }

    public void setNatureza_juridica(String natureza_juridica) {
        this.natureza_juridica = natureza_juridica;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEfr() {
        return efr;
    }

    public void setEfr(String efr) {
        this.efr = efr;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getMotivo_situacao() {
        return motivo_situacao;
    }

    public void setMotivo_situacao(String motivo_situacao) {
        this.motivo_situacao = motivo_situacao;
    }

    public String getSituacao_especial() {
        return situacao_especial;
    }

    public void setSituacao_especial(String situacao_especial) {
        this.situacao_especial = situacao_especial;
    }

    public String getData_situacao_especial() {
        return data_situacao_especial;
    }

    public void setData_situacao_especial(String data_situacao_especial) {
        this.data_situacao_especial = data_situacao_especial;
    }

    public String getCapital_social() {
        return capital_social;
    }

    public void setCapital_social(String capital_social) {
        this.capital_social = capital_social;
    }

    public String getQsa() {
        return qsa;
    }

    public void setQsa(String qsa) {
        this.qsa = qsa;
    }


    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    @Override
    public String toString() {
        return "CnpjEmpesa{" +
                "status='" + status + '\'' +
                ", boliing=" + boliing +
                ", cnpj='" + cnpj + '\'' +
                ", ultima_atualizacao='" + ultima_atualizacao + '\'' +
                ", tipo='" + tipo + '\'' +
                ", abertura='" + abertura + '\'' +
                ", nome='" + nome + '\'' +
                ", fantasia='" + fantasia + '\'' +
                ", atividade_principal='" + atividade_principal + '\'' +
                ", atividades_secundarias='" + atividades_secundarias + '\'' +
                ", natureza_juridica='" + natureza_juridica + '\'' +
                ", logradouro='" + logradouro + '\'' +
                ", numero='" + numero + '\'' +
                ", complemento='" + complemento + '\'' +
                ", cep='" + cep + '\'' +
                ", bairro='" + bairro + '\'' +
                ", municipio='" + municipio + '\'' +
                ", uf='" + uf + '\'' +
                ", porte='" + porte + '\'' +
                ", email='" + email + '\'' +
                ", telefone='" + telefone + '\'' +
                ", efr='" + efr + '\'' +
                ", situacao='" + situacao + '\'' +
                ", motivo_situacao='" + motivo_situacao + '\'' +
                ", situacao_especial='" + situacao_especial + '\'' +
                ", data_situacao_especial='" + data_situacao_especial + '\'' +
                ", capital_social='" + capital_social + '\'' +
                ", qsa='" + qsa + '\'' +
                ", extra='" + extra + '\'' +
                '}';
    }
}
