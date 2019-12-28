package com.comeze.rangelti.consultacnpj.views

import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.os.Vibrator
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.comeze.rangelti.consultacnpj.R
import kotlinx.android.synthetic.main.act_info.*
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper


class ActInfo : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.act_info)
        supportActionBar!!.hide()

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        flotBtnSair.setOnClickListener {
            startVibrat(90)
           finish()
        }
        floatBtnPolitic.setOnClickListener{
            startVibrat(90)
            val it = Intent(this, ActPolitica::class.java)
            startActivity(it)
        }

    }

    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }

    fun openBrowser(view: View) {

        //Get url from tag
        val url = view.tag as String

        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.addCategory(Intent.CATEGORY_BROWSABLE)

        //pass the url to intent data
        intent.data = Uri.parse(url)

        startActivity(intent)
    }

    override fun onBackPressed() {
        finish()
    }

    //Metudo que ativa vibração
    fun startVibrat(tempo: Long) { // cria um obj atvib que recebe seu valor de context
        val atvib = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        atvib.vibrate(tempo)
    }
}