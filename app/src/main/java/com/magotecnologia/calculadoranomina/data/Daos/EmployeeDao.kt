package com.magotecnologia.calculadoranomina.data.Daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.magotecnologia.calculadoranomina.data.Entities.EmployeeEntity
import com.magotecnologia.calculadoranomina.data.Mappers.EmployeeAndNovelties

/**
 * Created by Marco-Laptop on 24/11/2019.
 * Dao para la admnistración del empleado
 */

@Dao
interface EmployeeDao {
    @Insert
    suspend fun insert(employee: EmployeeEntity)

    @Update
    suspend fun update(vararg employee: EmployeeEntity)

    @Delete
    suspend fun delete(vararg employee: EmployeeEntity)

    @Query("SELECT * FROM " + EmployeeEntity.TABLE_NAME + " ORDER BY " + EmployeeEntity.DNI_COLUMN_NAME)
    fun getOrderedEmployeeListLive(): LiveData<List<EmployeeEntity>>

    @Query("SELECT * FROM " + EmployeeEntity.TABLE_NAME + " ORDER BY " + EmployeeEntity.DNI_COLUMN_NAME)
    suspend fun getOrderedEmployeeList(): List<EmployeeEntity>

    @Query("SELECT * FROM " + EmployeeEntity.TABLE_NAME + " WHERE " + EmployeeEntity.DNI_COLUMN_NAME + " =:dni")
    suspend fun getEmployeeByDNI(dni: Int): EmployeeEntity

    @Query("SELECT * FROM " + EmployeeEntity.TABLE_NAME + " WHERE " + EmployeeEntity.ID_COLUMN_NAME + " =:id")
    suspend fun getEmployeeById(id: Int): EmployeeEntity

    @Query("SELECT * FROM " + EmployeeEntity.TABLE_NAME + " WHERE " + EmployeeEntity.ID_COLUMN_NAME + " =:id")
    suspend fun getEmployeeAndNoveltiesById(id: Int): EmployeeAndNovelties

    @Query("SELECT * FROM " + EmployeeEntity.TABLE_NAME)
    suspend fun getAllEmployeeAndNovelties(): List<EmployeeAndNovelties>
}