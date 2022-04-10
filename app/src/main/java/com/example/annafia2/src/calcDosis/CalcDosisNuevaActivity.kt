package com.example.annafia2.src.calcDosis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.annafia2.R
import com.example.annafia2.db.medicine.Medicine
import java.io.StringWriter
import java.lang.Double.parseDouble

class CalcDosisNuevaActivity : AppCompatActivity() {

    lateinit var valueVol: TextView
    lateinit var valuePre: TextView
    var uno: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calc_dosis_nueva)

        //base de datos
//        Cdn = calc new dosis
        val titleCdn = findViewById<TextView>(R.id.textView2)
        val pre1 = findViewById<TextView>(R.id.textView8)
        val pre2 = findViewById<TextView>(R.id.textView9)
        val pre3 = findViewById<TextView>(R.id.textView10)
        val pre4 = findViewById<TextView>(R.id.textView11)
        val pre5 = findViewById<TextView>(R.id.textView12)

//        valores de los spinners
        valueVol = findViewById(R.id.textView13)
        valuePre = findViewById(R.id.textView15)

        //ingresa el usuario  peso volumen cc , cantidad de ampollas, dosis deseada
        val volumeCcSpiner = findViewById<Spinner>(R.id.spinner)
        val presentSpinner = findViewById<Spinner>(R.id.spinner2)
        val peso = findViewById<EditText>(R.id.editTextNumberDecimal).text.toString()
        val cantAmp = findViewById<EditText>(R.id.editTextNumberDecimal2).text.toString()
        val inpDosi = findViewById<EditText>(R.id.editTextNumberDecimal3).text.toString()


        val medicine = intent.getSerializableExtra("medicine") as Medicine

        val listPreSpinner = listOf(medicine.presentacion1,
            medicine.presentacion2,
            medicine.presentacion3,
            medicine.presentacion4,
            medicine.presentacion5)

        val listaVolumeCc = listOf("50", "100", "125", "150", "200", "250", "500", "1000")

        titleCdn.text = medicine.nombre
        pre1.text = medicine.presentacion1
        pre2.text = medicine.presentacion2
        pre3.text = medicine.presentacion3
        pre4.text = medicine.presentacion4
        pre5.text = medicine.presentacion5



        val volAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listaVolumeCc)
        volumeCcSpiner.adapter = volAdapter

        val preAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, listPreSpinner)
        presentSpinner.adapter = preAdapter


        volumeCcSpiner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                valueVol.text = listaVolumeCc[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        presentSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            presentSpinner.onItem
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                valuePre.text = getNumericValues(listPreSpinner[p2])
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        val a: String = presentSpinner.itemse

        valueVol.text =


//        microgramos total = presentacion x 1000 x cantidad de ampollas
//          concentracion de la disulucion = migrogramos total / volumen
//            infucion por hora = dodis deseada * peso real * 60 / consetacion de la disolucion

    }

//    a = presntacion , b = cantidad de ampollas , c = volumen cc, d = dosis deseada, e = peso real
    fun calDosis(a: Double, b: Double, c: Double , d: Double , e: Double): Double{

        val micTotal: Double = a * b * 1000
        val conDiso: Double = micTotal / c
        val infuHor: Double = (d * e * 60) / conDiso

        return infuHor
    }



/*    fun posSpiner(a: Spinner, b: Array<String>): String {
        var c = StringWriter()
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, b)
        a.adapter = adapter

        a.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                c = b
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        return c.toString();

    }*/


    fun getNumericValues(cadena: String): String {

        val sb = StringBuilder()

        for (i in cadena.indices) {
            var numeric = true
            try {
                val num = parseDouble(cadena[i].toString())
            } catch (e: NumberFormatException) {
                numeric = false
            }

            if (numeric) {
                sb.append(cadena[i].toString())
            } else {
                break
            }

        }

        return sb.toString();
    }

}