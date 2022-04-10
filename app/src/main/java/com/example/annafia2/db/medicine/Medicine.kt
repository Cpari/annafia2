package com.example.annafia2.db.medicine

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "medicine_table")
class Medicine(
    val nombre: String,
    val descripcion: String,
    val presentacion1: String,
    val presentacion2: String,
    val presentacion3: String,
    val presentacion4: String,
    val presentacion5: String,
    @PrimaryKey(autoGenerate = true)
    var idMedi: Int = 0,
): Serializable{
}