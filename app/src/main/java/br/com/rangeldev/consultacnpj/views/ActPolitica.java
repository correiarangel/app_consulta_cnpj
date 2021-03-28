package br.com.rangeldev.consultacnpj.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.WindowManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import br.com.rangeldev.consultacnpj.R;

public class ActPolitica extends AppCompatActivity {
    private WebView webPolitica;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_politica);
        webPolitica = findViewById(R.id.webPolitica);

        //desabilita rotaç~ao tela
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        //Empede que tela se apague
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        carregarWeb();
    }

    @Override
    public void finish() {
        super.finish();
    }

    public void carregarWeb(){
        WebSettings ws =  webPolitica.getSettings();
        ws.setJavaScriptEnabled(true);
        ws.setSupportZoom(false);

        webPolitica.loadUrl("file:///android_asset/politicaprivacidade.html");

        webPolitica.setWebViewClient(new WebViewClient(){
            public boolean ahouldOverrideUrlLoading(WebView webView,String url){
                return false;
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        finish();
    }
}
