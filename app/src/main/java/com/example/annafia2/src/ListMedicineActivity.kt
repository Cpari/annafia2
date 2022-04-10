package com.example.annafia2.src

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.lifecycle.Observer
import com.example.annafia2.R
import com.example.annafia2.db.medicine.AppDatabase
import com.example.annafia2.db.medicine.Medicine
import com.example.annafia2.db.medicine.MedicineAdapter
import com.example.annafia2.src.calcDosis.CalcDosisNuevaActivity

class ListMedicineActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_medicine)


        var listaMedicines = emptyList<Medicine>()

        val database = AppDatabase.getDatabase(this)
        val homeListMedicines = findViewById<ListView>(R.id.home_list_medicines)

        database.medicines().getAll().observe(this, Observer {

            listaMedicines = it
            val adapter = MedicineAdapter(this, listaMedicines)

            homeListMedicines.adapter = adapter

        })

        homeListMedicines.setOnItemClickListener { adapterView, view, i, l ->

            val intent = Intent(this, CalcDosisNuevaActivity::class.java)
            intent.putExtra("medicine", listaMedicines[i])
            startActivity(intent)

        }





    }
}