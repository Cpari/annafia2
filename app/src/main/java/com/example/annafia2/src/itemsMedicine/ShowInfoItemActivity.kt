package com.example.annafia2.src.itemsMedicine

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.annafia2.R
import com.example.annafia2.db.medicine.AppDatabase
import com.example.annafia2.db.medicine.Medicine
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShowInfoItemActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase
    private lateinit var medicine: Medicine
    private lateinit var medicineLiveData: LiveData<Medicine>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_info_item)

        val title = findViewById<TextView>(R.id.textView)

        database = AppDatabase.getDatabase(this)

        val idMedicine = intent.getIntExtra("id", 0)

        medicineLiveData = database.medicines().get(idMedicine)

        medicineLiveData.observe(this, Observer {
            medicine = it

            title.text = medicine.nombre


        })


    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflate: MenuInflater = menuInflater
        inflate.inflate(R.menu.menu_edit_delete, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.med_edit ->{

                val intent = Intent(this, FromControlActivity::class.java)
                intent.putExtra("medicine", medicine)
                startActivity(intent)

            }
            R.id.med_delete->{

                medicineLiveData.removeObservers(this)

                CoroutineScope(Dispatchers.IO).launch {
                    database.medicines().delete(medicine)
                    this@ShowInfoItemActivity.finish()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

}