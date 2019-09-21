package com.comeze.rangelti.consultacnpj.views.custom;




import com.comeze.rangelti.consultacnpj.views.model.CnpjEmpresa;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.OutputStream;


public class PrintPDF {

    public PrintPDF() {
    }

    public void geralPDF( CnpjEmpresa cnpjEmpresa ) throws Exception {
        Document doc = null;
        OutputStream os = null;

        try {
            //cria o documento tamanho A4, margens de 2,54cm
            doc = new Document(PageSize.A4, 72, 72, 72, 72);

            //cria a stream de saída
            os = new FileOutputStream("consultaCNPJ.pdf");

            //associa a stream de saída ao
            PdfWriter.getInstance(doc, os);


            //abre o documento
            doc.open();

            //adiciona o texto ao PDF
            Paragraph p0 = new Paragraph("---[ Cunsulta de CNPJ Retornada ]--");
            doc.add(p0);
            Paragraph p1 = new Paragraph("");
            doc.add(p1);
            Paragraph p2 = new Paragraph("CNPJ :"+ cnpjEmpresa.getCnpj());
            doc.add(p2);
            Paragraph p3 = new Paragraph("Razão Social :"+ cnpjEmpresa.getNome());
            doc.add(p3);
            Paragraph p8 = new Paragraph("Nome Fantasia:"+ cnpjEmpresa.getFantasia());
            doc.add(p8);
            Paragraph p4 = new Paragraph("Status :"+ cnpjEmpresa.getStatus());
            doc.add(p4);
            Paragraph p5 = new Paragraph("Ultima Atualização :"+ cnpjEmpresa.getUltima_atualizacao());
            doc.add(p5);
            Paragraph p6 = new Paragraph("Tipo :"+ cnpjEmpresa.getTipo());
            doc.add(p6);
            Paragraph p7 = new Paragraph(":"+ cnpjEmpresa.getAbertura());
            doc.add(p7);
            Paragraph p9 = new Paragraph("Atividade principal:"+ cnpjEmpresa.getAtividade_principal());
            doc.add(p9);
            Paragraph p10 = new Paragraph(":Natureza Juridica"+ cnpjEmpresa.getNatureza_juridica());
            doc.add(p10);
            Paragraph p11 = new Paragraph("Longradouro:"+ cnpjEmpresa.getLogradouro());
            doc.add(p11);
            Paragraph p12 = new Paragraph("Numero :"+ cnpjEmpresa.getNumero());
            doc.add(p12);
            Paragraph p13 = new Paragraph("Complemento :"+ cnpjEmpresa.getComplemento());
            doc.add(p13);
            Paragraph p14 = new Paragraph("Bairro :"+ cnpjEmpresa.getBairro());
            doc.add(p14);
            Paragraph p15 = new Paragraph("CEP :"+ cnpjEmpresa.getCep());
            doc.add(p15);
            Paragraph p16 = new Paragraph("Municipio :"+ cnpjEmpresa.getMunicipio());
            doc.add(p16);
            Paragraph p17 = new Paragraph("Estado :"+ cnpjEmpresa.getUf());
            doc.add(p17);
            Paragraph p18 = new Paragraph("Porte :"+ cnpjEmpresa.getPorte());
            doc.add(p18);
            Paragraph p19 = new Paragraph("Email :"+ cnpjEmpresa.getEmail());
            doc.add(p19);
            Paragraph p20 = new Paragraph("Fone :"+ cnpjEmpresa.getTelefone());
            doc.add(p20);
            Paragraph p21 = new Paragraph("Situação :"+ cnpjEmpresa.getSituacao());
            doc.add(p21);
            Paragraph p22 = new Paragraph("Motivo Situação :"+ cnpjEmpresa.getMotivo_situacao());
            doc.add(p22);
            Paragraph p23 = new Paragraph("Situação Especial :"+ cnpjEmpresa.getSituacao_especial());
            doc.add(p23);
            Paragraph p24 = new Paragraph("Capital Social :"+ cnpjEmpresa.getCapital_social());
            doc.add(p24);
            Paragraph p25 = new Paragraph("Quadro de Socios :"+ cnpjEmpresa.getQsa());
            doc.add(p25);



        } finally {
            if (doc != null) {
                //fechamento do documento
                doc.close();
            }
            if (os != null) {
                //fechamento da stream de saída
                os.close();
            }
        }
    }
}