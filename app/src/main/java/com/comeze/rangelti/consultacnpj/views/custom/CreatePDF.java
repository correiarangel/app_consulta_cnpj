package com.comeze.rangelti.consultacnpj.views.custom;

import android.os.Environment;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.comeze.rangelti.consultacnpj.views.model.CnpjEmpresa;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import java.util.UUID;

public class CreatePDF extends AppCompatActivity {

    private CnpjEmpresa empresa;


    public CreatePDF( CnpjEmpresa empresa) {

        this.empresa = empresa;

    }

    public void gerar( ) throws Exception {

        Document doc = null;
        FileOutputStream os = null;

        try {
            //cria o documento tamanho A4, margens de 2,54cm
            doc = new Document(PageSize.A4, 72, 72, 72, 72);

            //local do arquivo gerado
            String directory_path = Environment.getExternalStorageDirectory().getPath() + "/";

            File file = new File(directory_path);
            if (!file.exists()) {
                file.mkdirs();
            }

            //cria a stream de saída com nome unico
            UUID uuid = UUID.randomUUID();

            String n_arq = uuid.toString();
            String targetPdf = directory_path+"ConsulaCNPJ:"+n_arq+".pdf";
            File filePath = new File(targetPdf);

            try {

                //cria a stream de saída associa a stream de saída
                os = new FileOutputStream(filePath);
                PdfWriter.getInstance(doc, os);
                //abre o documento
                doc.open();
                System.setProperty("MSG","OK");

            } catch (IOException e) {
                Log.e("main", "error "+e.toString());

                System.setProperty("MSG","ERROR");

            }

            //capitura data e formata
            SimpleDateFormat dateFormat = new SimpleDateFormat ( "dd/MM/yyyy" );
            Calendar cal = Calendar.getInstance ( );
            Date data_atual = cal.getTime ( );

            String dataAtual = dateFormat.format ( data_atual );

            //adiciona o texto ao PDF
            Paragraph p = new Paragraph("CONSULTA DE  CNPJ  :"+dataAtual+"\n"+"\n");

            p.add("CNPJ :"+empresa.getCnpj()+"\n");
            p.add("Razão Social :"+empresa.getNome()+"\n");
            p.add("Nome Fantasia:"+empresa.getFantasia()+"\n");
            p.add("Status :"+empresa.getStatus()+"\n");

            p.add("Ultima Atualização :"+ empresa.getUltima_atualizacao()+"\n");
            p.add("Tipo :"+empresa.getTipo()+"\n");
            p.add("Data Abetura :"+ empresa.getAbertura()+"\n");
            p.add("Atividade principal:"+empresa.getAtividade_principal()+"\n");
            p.add("Natureza Juridica :"+empresa.getNatureza_juridica()+"\n");
            p.add("Longradouro:"+ empresa.getLogradouro()+"\n");
            p.add("Numero :"+ empresa.getNumero()+"\n");
            p.add("Complemento :"+ empresa.getComplemento()+"\n");
            p.add("Bairro :"+ empresa.getBairro()+"\n");
            p.add("CEP :"+ empresa.getCep()+"\n");
            p.add("Municipio :"+ empresa.getMunicipio()+"\n");
            p.add("Estado :"+ empresa.getUf()+"\n");
            p.add("Porte :"+ empresa.getPorte()+"\n");
            p.add("Email :"+ empresa.getEmail()+"\n");
            p.add("Fone :"+ empresa.getTelefone()+"\n");
            p.add("Situação :"+ empresa.getSituacao()+"\n");
            p.add("Motivo Situação:"+ empresa.getMotivo_situacao()+"\n");
            p.add("Situação Especial :"+ empresa.getSituacao_especial()+"\n");

            p.add("Capital Social :"+ empresa.getQsa()+"\n");
            p.add("Quadro de Socios :"+ empresa.getCapital_social()+"\n");

            p.add("\n\n");
            p.add("Desenvolvido por : Marcos F C Rangel e Wendreo Fernandes\n");
            p.add("Contato:\n");
            p.add("E-mail: correiarangel@bol.com.br ,Marcos F C Rangel\n");
            p.add("E-mail: wendreolf@gmail.com ,Wendreo Fernandes");

            doc.add( p );

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
