package com.example.annafia2.src

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.LinearLayout
import com.example.annafia2.R
import com.example.annafia2.src.itemsMedicine.HomeItemsListActivity

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val btnDosisNueva = findViewById<LinearLayout>(R.id.linearLayout)
        val btnDosisEntrante = findViewById<LinearLayout>(R.id.linearLayout2)
        val btnPacientes = findViewById<LinearLayout>(R.id.linearLayout3)



        btnDosisNueva.setOnClickListener {
            val intent = Intent(this, ListMedicineActivity::class.java)
            intent.putExtra("dosis", "dosis_nueva")
            startActivity(intent)
        }

        btnDosisEntrante.setOnClickListener {
            val intent = Intent(this, ListMedicineActivity::class.java)
            intent.putExtra("dosis", "dosis_entrante")
            startActivity(intent)
        }


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate : MenuInflater =  menuInflater
        inflate.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.mm_medicine -> {
                val intent = Intent(this, HomeItemsListActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}