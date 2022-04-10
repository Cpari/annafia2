package com.example.annafia2.src.itemsMedicine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.MediaController
import com.example.annafia2.R
import com.example.annafia2.db.medicine.AppDatabase
import com.example.annafia2.db.medicine.Medicine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FromControlActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_from_control)

        val nameMedi = findViewById<EditText>(R.id.editTextTextPersonName)
        val descriMedi = findViewById<EditText>(R.id.editTextTextPersonName2)
        val pre1Medi = findViewById<EditText>(R.id.editTextTextPersonName3)
        val pre2Medi = findViewById<EditText>(R.id.editTextTextPersonName4)
        val pre3Medi = findViewById<EditText>(R.id.editTextTextPersonName5)
        val pre4Medi = findViewById<EditText>(R.id.editTextTextPersonName6)
        val pre5Medi = findViewById<EditText>(R.id.editTextTextPersonName7)

        val btnSave = findViewById<Button>(R.id.button2)

        var idMedicine: Int? = null

        if (intent.hasExtra("medicine")) {
            val medicine = intent.extras?.getSerializable("medicine") as Medicine
            nameMedi.setText(medicine.nombre)
            descriMedi.setText(medicine.descripcion)
            pre1Medi.setText(medicine.presentacion1)
            pre2Medi.setText(medicine.presentacion2)
            pre3Medi.setText(medicine.presentacion3)
            pre4Medi.setText(medicine.presentacion4)
            pre5Medi.setText(medicine.presentacion5)

            idMedicine = medicine.idMedi
        }


        val database = AppDatabase.getDatabase(this)

        btnSave.setOnClickListener {

            val name = nameMedi.text.toString()
            val descri = descriMedi.text.toString()
            val pre1 = pre1Medi.text.toString()
            val pre2 = pre2Medi.text.toString()
            val pre3 = pre3Medi.text.toString()
            val pre4 = pre4Medi.text.toString()
            val pre5 = pre5Medi.text.toString()

            val item = Medicine(name, descri, pre1, pre2, pre3, pre4, pre5)

            if (idMedicine != null) {
                CoroutineScope(Dispatchers.IO).launch {
                    item.idMedi = idMedicine
                    database.medicines().update(item)
                    this@FromControlActivity.finish()
                }
            } else {

                CoroutineScope(Dispatchers.IO).launch {
                    database.medicines().insertAll(item)
                    this@FromControlActivity.finish()
                }
            }

        }

    }
}