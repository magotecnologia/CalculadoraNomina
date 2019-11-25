package com.magotecnologia.calculadoranomina.data.Daos

import androidx.lifecycle.LiveData
import androidx.room.*
import com.magotecnologia.calculadoranomina.data.Entities.NoveltyEntity

/**
 * Created by Marco-Laptop on 24/11/2019.
 * Dao para acceder a los datos de las novedades
 */

@Dao
interface NoveltyDao {
    @Insert
    suspend fun insert(noveltyEntity: NoveltyEntity)

    @Update
    suspend fun update(vararg noveltyEntity: NoveltyEntity)

    @Delete
    suspend fun delete(vararg noveltyEntity: NoveltyEntity)

    @Query("SELECT * FROM " + NoveltyEntity.TABLE_NAME + " ORDER BY " + NoveltyEntity.DATE_COLUMN_NAME)
    fun getOrderedNoveltyListLive(): LiveData<List<NoveltyEntity>>

    @Query("SELECT * FROM " + NoveltyEntity.TABLE_NAME + " ORDER BY " + NoveltyEntity.DATE_COLUMN_NAME)
    suspend fun getOrderedNoveltyList(): List<NoveltyEntity>

    @Query(
        "SELECT * FROM " + NoveltyEntity.TABLE_NAME + " WHERE " +
                NoveltyEntity.MONTH_COLUMN_NAME + " =:month" + " ORDER BY " + NoveltyEntity.DATE_COLUMN_NAME
    )
    suspend fun getNoveltiesByMonth(month: Int): List<NoveltyEntity>

    @Query("SELECT * FROM " + NoveltyEntity.TABLE_NAME + " WHERE " + NoveltyEntity.EMPLOYEE_COLUMN_NAME + " =:employeeId" + " ORDER BY " + NoveltyEntity.DATE_COLUMN_NAME)
    suspend fun getNoveltiesByEmployee(employeeId: Int): List<NoveltyEntity>

    @Query(
        "SELECT * FROM " + NoveltyEntity.TABLE_NAME +
                " WHERE " + NoveltyEntity.EMPLOYEE_COLUMN_NAME + " =:employeeId" +
                " AND " + NoveltyEntity.MONTH_COLUMN_NAME + " =:month" +
                " ORDER BY " + NoveltyEntity.DATE_COLUMN_NAME
    )
    suspend fun getNoveltiesByEmployeeAndMonth(employeeId: Int, month: Int): List<NoveltyEntity>

}