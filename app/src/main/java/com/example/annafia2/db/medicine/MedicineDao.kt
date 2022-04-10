package com.example.annafia2.db.medicine

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MedicineDao {

    @Query("SELECT * FROM medicine_table")
    fun getAll(): LiveData<List<Medicine>>

    @Query("SELECT * FROM medicine_table WHERE idMedi =  :id")
    fun get(id: Int): LiveData<Medicine>

    @Insert
    fun insertAll(vararg medicines: Medicine)

    @Update
    fun update(medicine: Medicine)

    @Delete
    fun delete(medicine: Medicine)

}