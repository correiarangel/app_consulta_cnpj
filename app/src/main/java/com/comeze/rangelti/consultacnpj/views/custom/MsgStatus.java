package com.comeze.rangelti.consultacnpj.views.custom;

import android.content.Context;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MsgStatus extends AppCompatActivity {

    private Context context;

    public MsgStatus(Context context) {
        this.context = context;
    }

    public void msgStatusCode(String keyMsg){

        switch ( keyMsg ){
            case "No value for cnpj" :
                startMsg("CNPJ infomado é invalido!");break;
            case "200" :
                startMsg("Carregando dados...");break;
            case "300" :
                startMsg("A requisição tem mais de uma resposta possível..");break;
            case "302" :
                startMsg("Esse código de resposta significa que a URI do recurso requerido foi mudada .");break;
            case "204" :
                startMsg("Não há conteúdo para enviar para esta solicitação.");break;
            case "400" :
                startMsg("Servidor não entendeu a requisição");break;
            case "403" :
                startMsg("O cliente não tem direitos de acesso ao conteúdo"); break;
            case "404" :
                startMsg("O servidor não pode encontrar o recurso solicitado"); break;
            case "405" :
                startMsg("O método de solicitação foi desativado e não pode ser usado ");
                startMsg("Contate o desenvolvedor na pagina info");break;
            case "406" :
                startMsg("Não encontra nenhum conteúdo seguindo os critérios fornecidos");break;
            case "408" :
                startMsg("Sem resposta do servidor o tempo foi excedido ");break;
            case "423" :
                startMsg("O recurso sendo acessado está travado.");break;
            case "424" :
                startMsg("A requisição falhou devido a falha em requisição prévia.");break;
            case "500" :
                startMsg("O servidor encontrou uma situação com a qual não sabe lidar.");break;
        }
    }

    //msg
    public void startMsg(String message ) {

        int duration = Toast.LENGTH_LONG;
        Toast toast  = Toast.makeText(context, message, duration);
        toast.show();

    }
    public void setMsg() {

        String s = System.getProperty("MSG");
        if ( s.equals("OK")) {
            startMsg("Arquivo gerado com sucesso na raiz do seu dispositivo!");

        }else {
            startMsg("Falha na geração do arquivo:");
        }
    }

}
