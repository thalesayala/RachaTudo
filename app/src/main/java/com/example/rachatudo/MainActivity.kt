package com.example.rachatudo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.widget.TextView
import android.widget.EditText
import android.content.Intent
import android.widget.ImageButton
import androidx.core.widget.addTextChangedListener


class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    var tts: TextToSpeech? = null
    var resultado: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val shareButton: ImageButton = findViewById(R.id.share2)
        val speakButton: ImageButton = findViewById(R.id.speak1)
        val conta: EditText = findViewById(R.id.valorConta)
        val pessoas: EditText = findViewById(R.id.numPes)
        resultado = findViewById(R.id.conta)

        tts = TextToSpeech(this, this)

        fun pagamento() {
            var valorT = 0.0
            var pessoasT = 0.0
            if (conta.text.isNotEmpty()) {
                val valorTstr: String = conta.text.toString()
                valorT = valorTstr.toDouble()
            }
            if (pessoas.text.isNotEmpty()) {
                val pessoasTstr: String = pessoas.text.toString()
                pessoasT = pessoasTstr.toDouble()
            }
            if (conta.text.isNotEmpty() and pessoas.text.isNotEmpty()) {
                val valorF: Double = valorT / pessoasT
                var valorFstr: String = valorF.toString()
                if (valorFstr == "Infinity") {
                    valorFstr = "0.0"
                }
                resultado!!.text = ("O valor para cada infeliz é ${valorFstr} reais")

            }
            if (conta.text.isEmpty() && pessoas.text.isEmpty()) {
                resultado!!.text= "0.0"

            }
        }
        conta.addTextChangedListener() {
            pagamento()
        }

        pessoas.addTextChangedListener() {
            pagamento()

        }


        shareButton.setOnClickListener()
        {
            val myIntent = Intent(Intent.ACTION_SEND)
            myIntent.type = "type/palin"
            val shareBody = "Resultado do Racha Tudo"
            val shareSub =
                "A divisÃ£o de pagamento resultou em: " + resultado!!.text.toString() + " para cada Infeliz."
            myIntent.putExtra(Intent.EXTRA_SUBJECT, shareBody)
            myIntent.putExtra(Intent.EXTRA_TEXT, shareSub)
            startActivity(Intent.createChooser(myIntent, "Compartilhe a Conta"))
        }
        speakButton.setOnClickListener()
        {
            val text = resultado!!.text.toString()
            tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
        }
        tts = TextToSpeech(this, this)

    }

    override fun onInit(status: Int) {

    }
}














