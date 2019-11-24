package com.magotecnologia.calculadoranomina.data.Daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.magotecnologia.calculadoranomina.data.Entities.EmployeeEntity

/**
 * Created by Marco-Laptop on 24/11/2019.
 * Dao para la admnistración del empleado
 */

@Dao
interface EmployeeDao {
    @Insert
    fun insert(employee: EmployeeEntity)

    @Update
    fun update(vararg employee: EmployeeEntity)

    @Delete
    fun delete(vararg employee: EmployeeEntity)

    @Query("SELECT * FROM " + EmployeeEntity.TABLE_NAME + " ORDER BY " + EmployeeEntity.DNI_COLUMN_NAME)
    fun getOrderedEmployeeListLive(): LiveData<List<EmployeeEntity>>

    @Query("SELECT * FROM " + EmployeeEntity.TABLE_NAME + " ORDER BY " + EmployeeEntity.DNI_COLUMN_NAME)
    fun getOrderedEmployeeList(): List<EmployeeEntity>

    @Query("SELECT * FROM " + EmployeeEntity.TABLE_NAME + " WHERE " + EmployeeEntity.DNI_COLUMN_NAME + " =:dni")
    fun getEmployeeByDNI(dni: Int): EmployeeEntity

}