package com.example.annafia2.src.calcDosis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.annafia2.R

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val resultado = intent.getStringExtra("resultado")

        val showResult = findViewById<TextView>(R.id.textView16)




        showResult.text = resultado




    }
}