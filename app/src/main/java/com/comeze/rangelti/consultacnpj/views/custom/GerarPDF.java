package com.comeze.rangelti.consultacnpj.views.custom;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.util.Log;


import androidx.appcompat.app.AppCompatActivity;

import com.comeze.rangelti.consultacnpj.views.ActConsulta;
import com.comeze.rangelti.consultacnpj.views.model.CnpjEmpresa;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class GerarPDF extends AppCompatActivity {

    ActConsulta actConsulta ;

    public GerarPDF() {
        actConsulta = new ActConsulta();
    }

    //metudo gera pdf
    public void createPdf(CnpjEmpresa empresa){

        // criando o documento novo

        PdfDocument document = new PdfDocument();

        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();

        PdfDocument.Page page = document.startPage(pageInfo);

        // cria pagina
        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();

        SimpleDateFormat dateFormat = new SimpleDateFormat ( "dd/MM/yyyy" );
        Calendar cal = Calendar.getInstance ( );
        Date data_atual = cal.getTime ( );

        String dataAtual = dateFormat.format ( data_atual );


        canvas.drawText(" Cunsulta de CNPJ Data:"+dataAtual, 30, 60, paint);

        canvas.drawLine(30,65,250,65,paint);//line

        canvas.drawText("CNPJ"+empresa.getCnpj() ,30, 80, paint);
        canvas.drawText("Razão Social :"+empresa.getNome(), 30, 95, paint);
        canvas.drawText("Nome Fantasia:"+empresa.getFantasia(), 30,110,paint);
        //canvas.drawText("Nome Fantasia:"+empresa.getFantasia(), 30, 100, paint);
        canvas.drawText("Status :"+empresa.getStatus(), 30, 125, paint);
        canvas.drawText("Ultima Atualização :"+ empresa.getUltima_atualizacao(), 30, 140, paint);
        canvas.drawText("Tipo :"+empresa.getTipo(), 30, 155, paint);
        canvas.drawText("Data Abetura :"+ empresa.getAbertura(), 30, 170, paint);
        canvas.drawText("Atividade principal:", 30 ,185,paint);
        canvas.drawText(""+empresa.getAtividade_principal(), 30, 200,paint);

        canvas.drawText("Natureza Juridica :", 30, 215,paint);
        canvas.drawText( ""+empresa.getNatureza_juridica(), 30, 230,paint);
        //canvas.drawText("Natureza Juridica :"+ empresa.getNatureza_juridica(), 30, 220, paint);
        canvas.drawText("Longradouro:"+ empresa.getLogradouro(),30, 245, paint);
        canvas.drawText("Numero :"+ empresa.getNumero(), 30, 260, paint);
        canvas.drawText("Complemento :"+ empresa.getComplemento(), 30, 275, paint);
        canvas.drawText("Bairro :"+ empresa.getBairro(), 30, 290, paint);
        canvas.drawText("CEP :"+ empresa.getCep(), 30, 305, paint);
        canvas.drawText("Municipio :"+ empresa.getMunicipio(), 30,320 , paint);
        canvas.drawText("Estado :"+ empresa.getUf(), 30, 335, paint);
        canvas.drawText("Porte :"+ empresa.getPorte(), 30, 350, paint);
        canvas.drawText("Email :"+ empresa.getEmail(), 30,365, paint);
        canvas.drawText("Fone :"+ empresa.getTelefone(), 30, 380, paint);
        canvas.drawText("Situação :"+ empresa.getSituacao(), 30, 395, paint);
        canvas.drawText("Motivo Situação:"+ empresa.getMotivo_situacao(), 30, 410, paint);
        canvas.drawText("Situação Especial :"+ empresa.getSituacao_especial(), 30, 425, paint);
        canvas.drawText("Capital Social :"+ empresa.getCapital_social(), 30, 440, paint);
        canvas.drawText("Quadro de Socios :"+ empresa.getQsa(), 30, 455, paint);

        canvas.drawLine(30,460,250,460,paint);//line


        document.finishPage(page);

        //local do arquivo gerado
        String directory_path = Environment.getExternalStorageDirectory().getPath() + "/";
        File file = new File(directory_path);
        if (!file.exists()) {
            file.mkdirs();
        }

        Random gerador = new Random();
        int n_arq = gerador.nextInt(50);
        String targetPdf = directory_path+"ConsultaCNPJ:"+n_arq+".pdf";
        File filePath = new File(targetPdf);
        try {

            document.writeTo(new FileOutputStream(filePath));
            System.setProperty("MSG","OK");

        } catch (IOException e) {
            Log.e("main", "error "+e.toString());

            System.setProperty("MSG","ERROR");

        }

        document.close();
    }

    //msg


}