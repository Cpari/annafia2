package com.example.annafia2.src.itemsMedicine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.ListView
import androidx.lifecycle.Observer
import com.example.annafia2.R
import com.example.annafia2.db.medicine.AppDatabase
import com.example.annafia2.db.medicine.Medicine
import com.example.annafia2.db.medicine.MedicineAdapter

class HomeItemsListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_items_list)

        val formListitems = findViewById<ListView>(R.id.form_list_items)

        var listaItems = emptyList<Medicine>()
        val database = AppDatabase.getDatabase(this)

        database.medicines().getAll().observe(this, Observer {
            listaItems = it
            val adapter = MedicineAdapter(this, listaItems)
            formListitems.adapter  = adapter
        })

        formListitems.setOnItemClickListener { adapterView, view, i, l ->
            val intent = Intent(this, ShowInfoItemActivity::class.java)
            intent.putExtra("id", listaItems[i].idMedi)
            startActivity(intent)
        }



    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate: MenuInflater = menuInflater
        inflate.inflate(R.menu.menu_add_item, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.ma_add_item -> {
                val intent = Intent(this, FromControlActivity::class.java)
                startActivity(intent)
            }
        }
        return super.onOptionsItemSelected(item)
    }


}