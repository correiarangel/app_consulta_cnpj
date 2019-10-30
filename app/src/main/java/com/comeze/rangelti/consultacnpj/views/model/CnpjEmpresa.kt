package com.comeze.rangelti.consultacnpj.views.model



data class CnpjEmpresa(
        var status: String?, // boliing Indica para a requisição como foi registrado a cobrança da consulta.
        var boliing: String?, var cnpj: String?,
        var ultima_atualizacao: String?, var tipo: String?, var abertura: String?,
        var nome: String?, var fantasia: String?, var atividade_principal: String?,
        var atividades_secundarias: String?, var natureza_juridica: String?,
        var logradouro: String?, var numero: String?, var complemento: String?, var cep: String?,
        var bairro: String?, var municipio: String?, var uf: String?, var porte: String?, var email: String?,
        var telefone: String?, //efr Ente Federativo Responsável, disponibilizado apenas
        //para CNPJs da administração pública.
        var efr: String?, var situacao: String?, var motivo_situacao: String?,
        var situacao_especial: String?, var data_situacao_especial: String?, var capital_social: String?,
        var qsa: String? //Quadro de Sócios e Administradores.
        , //  private String qual;
        var extra: String?)//this.qual= qual;


