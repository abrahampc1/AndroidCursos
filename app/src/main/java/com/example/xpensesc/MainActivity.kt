package com.example.xpensesc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.util.Locale

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {

    var tts: TextToSpeech? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tts = TextToSpeech(this,this)

        findViewById<Button>(R.id.btnPlay).setOnClickListener{speak()}

    }

    private fun speak(){
        var message: String = findViewById<EditText>(R.id.EtMessage).text.toString()

        if (message.isEmpty()){
            findViewById<TextView>(R.id.textViewHolaKotlin).text = "Introduzca un texto! =)"
            message = "por favor, escribe un texto"
        }

        tts!!.speak(message, TextToSpeech.QUEUE_FLUSH, null,"")
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS){
            findViewById<TextView>(R.id.textViewHolaKotlin).text = "Servicio disponible"
            tts!!.setLanguage(Locale("ES"))
        } else{
            findViewById<TextView>(R.id.textViewHolaKotlin).text = "No disponible :("
        }
    }

    override fun onDestroy() {
        if (tts != null){
            tts!!.stop()
            tts!!.shutdown()
        }
        super.onDestroy()
    }
}