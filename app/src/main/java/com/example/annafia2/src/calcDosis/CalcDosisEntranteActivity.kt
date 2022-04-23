package com.example.annafia2.src.calcDosis

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Spinner
import com.example.annafia2.R
import com.example.annafia2.db.medicine.AppDatabase
import com.example.annafia2.db.medicine.AppDatabase_Impl
import com.example.annafia2.db.medicine.Medicine
import com.example.annafia2.db.medicine.MedicineDao_Impl

class CalcDosisEntranteActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_calc_dosis_entrante)


//    spinners
    val spinPre = findViewById<Spinner>(R.id.spinner3)
    val spinVol = findViewById<Spinner>(R.id.spinner4)

//    textviews
    val infucionActual = findViewById<EditText>(R.id.editTextNumberDecimal4)
    val presoReal = findViewById<EditText>(R.id.editTextNumberDecimal5)
    val cantAmpollas = findViewById<EditText>(R.id.editTextNumberDecimal4)

//    val database = AppDatabase.getDatabase(this)

    val medicine = intent.getSerializableExtra("medicine_en") as Medicine








  }

//    microgramos total = presentacion x 1000 x cantidad de ampollas
//    concentracion de la disulucion = migrogramos total / volumen
//    infucion por hora = dodis deseada * peso real * 60 / consetacion de la disolucion
//    a = presntacion , b = cantidad de ampollas , c = volumen cc, d = dosis deseada, e = peso real

  private fun calDosis(a: Double, b: Double, c: Double , d: Double , e: Double): Double{

    val micTotal: Double = a * b * 1000
    val conDiso: Double = micTotal / c
    val dosisIngresante: Double = (d * e * 60) / conDiso

    return dosisIngresante
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
        val num = java.lang.Double.parseDouble(cadena[i].toString())
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