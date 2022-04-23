package com.example.annafia2.src.calcDosis

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.annafia2.R
import com.example.annafia2.db.medicine.Medicine
import java.lang.Double.parseDouble


class CalcDosisNuevaActivity : AppCompatActivity() {

    lateinit var valueVol: TextView
    lateinit var valuePre: TextView

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
        val peso = findViewById<EditText>(R.id.editTextNumberDecimal)
        val cantAmp = findViewById<EditText>(R.id.editTextNumberDecimal2)
        val inpDosi = findViewById<EditText>(R.id.editTextNumberDecimal3)
        val btnCalDosis = findViewById<Button>(R.id.button3)


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
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                valuePre.text = listPreSpinner[p2]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }

        val a = presentSpinner.selectedItem.toString()
        val c = volumeCcSpiner.selectedItem.toString()

        val ca: Double = getNumericValues(a).toDouble()
        val cc: Double = c.toDouble()


        btnCalDosis.setOnClickListener {


            if(validarCampos(cantAmp, inpDosi, peso)){
                val cb: Double = (cantAmp.text.toString()).toDouble()
                val cd: Double = (inpDosi.text.toString()).toDouble()
                val ce: Double = (peso.text.toString()).toDouble()
                println(calDosis(ca,cb,cc,cd,ce))
                val resultado = calDosis(ca,cb,cc,cd,ce)
                val intent = Intent(this, ResultActivity::class.java)
                intent.putExtra("resultado", resultado.toString())
                startActivity(intent)
            }else{
                Toast.makeText(this, "No deje ningun campo vacio", Toast.LENGTH_SHORT).show()
            }

        }


    }


//    microgramos total = presentacion x 1000 x cantidad de ampollas
//    concentracion de la disulucion = migrogramos total / volumen
//    infucion por hora = dodis deseada * peso real * 60 / consetacion de la disolucion
//    a = presntacion , b = cantidad de ampollas , c = volumen cc, d = dosis deseada, e = peso real

    private fun calDosis(a: Double, b: Double, c: Double , d: Double , e: Double): Double{

        val micTotal: Double = a * b * 1000
        val conDiso: Double = micTotal / c
        val infuHor: Double = (d * e * 60) / conDiso

        return infuHor
    }

//    funcion para validar los campos
    private fun validarCampos(a: EditText, b: EditText, c: EditText): Boolean{
        var result: Boolean = true
        if (a.length() == 0 || b.length() == 0 || c.length() == 0){
            result = false
        }
        return result
    }


//    funcion que optinen los numeros de un string
    private fun getNumericValues(cadena: String): String {

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