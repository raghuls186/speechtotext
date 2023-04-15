package com.example.speechtotext

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.Button
import android.widget.TextView
import java.util.Locale

class MainActivity : AppCompatActivity() {
    lateinit var btMicOn : Button
    lateinit var tvText : TextView
    private  val REQUEST_CODE_SPEECH_INPUT=100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btMicOn = findViewById(R.id.btMicOn)
        tvText = findViewById(R.id.tvText)
        btMicOn.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)

            intent.putExtra(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
            )
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello Speak...!")
            startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CODE_SPEECH_INPUT && resultCode == RESULT_OK && data !=null)
        {
            var res = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
            var text = res?.get(0)
            tvText.text = text
        }
    }
}
