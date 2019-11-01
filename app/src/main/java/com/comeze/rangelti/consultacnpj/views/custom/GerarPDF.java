package com.comeze.rangelti.consultacnpj.views.custom;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.comeze.rangelti.consultacnpj.views.ActConsulta;
import com.comeze.rangelti.consultacnpj.views.model.CnpjEmpresa;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;


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

        Date dataAtual = new Date();

        canvas.drawText("[ Cunsulta de CNPJ Retornada Data:"+dataAtual +"]", 20, 30, paint);
        canvas.drawText("-------------------------------------------------------------------------", 19, 40, paint);
        canvas.drawText("CNPJ"+empresa.getCnpj() ,20, 60, paint);
        canvas.drawText("Razão Social :"+empresa.getNome(), 20, 80, paint);

        canvas.drawText("Nome Fantasia:"+empresa.getFantasia(), 20, 100, paint);
        canvas.drawText("Status :"+empresa.getStatus(), 20, 120, paint);
        canvas.drawText("Ultima Atualização :"+ empresa.getUltima_atualizacao(), 20, 140, paint);
        canvas.drawText("Tipo :"+empresa.getTipo(), 20, 160, paint);
        canvas.drawText("Data Abetura :"+ empresa.getAbertura(), 20, 180, paint);
        canvas.drawText("Atividade principal:\n"+empresa.getAtividade_principal(), 20, 200, paint);
        canvas.drawText(":Natureza Juridica :\n"+ empresa.getNatureza_juridica(), 20, 220, paint);
        canvas.drawText("Longradouro:"+ empresa.getLogradouro(),20, 240, paint);
        canvas.drawText("Numero :"+ empresa.getNumero(), 20, 260, paint);
        canvas.drawText("Complemento :"+ empresa.getComplemento(), 20, 280, paint);
        canvas.drawText("Bairro :"+ empresa.getBairro(), 20, 300, paint);
        canvas.drawText("CEP :"+ empresa.getCep(), 20, 320, paint);
        canvas.drawText("Municipio :"+ empresa.getMunicipio(), 20,340 , paint);
        canvas.drawText("Estado :"+ empresa.getUf(), 20, 360, paint);
        canvas.drawText("Porte :"+ empresa.getPorte(), 20, 380, paint);
        canvas.drawText("Email :"+ empresa.getEmail(), 20,400, paint);
        canvas.drawText("Fone :"+ empresa.getTelefone(), 20, 420, paint);
        canvas.drawText("Situação :"+ empresa.getSituacao(), 20, 440, paint);
        canvas.drawText("Motivo Situação:"+ empresa.getMotivo_situacao(), 20, 460, paint);
        canvas.drawText("Situação Especial :"+ empresa.getSituacao_especial(), 20, 480, paint);
        canvas.drawText("Capital Social :"+ empresa.getCapital_social(), 20, 500, paint);
        canvas.drawText("Quadro de Socios :"+ empresa.getQsa(), 20, 520, paint);


        document.finishPage(page);



        String directory_path = Environment.getExternalStorageDirectory().getPath() + "/";
        File file = new File(directory_path);
        if (!file.exists()) {
            file.mkdirs();
        }

        String targetPdf = directory_path+"CNPJ.pdf";
        File filePath = new File(targetPdf);
        try {

            document.writeTo(new FileOutputStream(filePath));
           // actConsulta.setMsg("OK");

        } catch (IOException e) {
            Log.e("main", "error "+e.toString());


            //actConsulta.setMsg("ERROR");
        }

        document.close();
    }

    //msg


}