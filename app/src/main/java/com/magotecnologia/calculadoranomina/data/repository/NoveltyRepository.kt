package com.magotecnologia.calculadoranomina.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.magotecnologia.calculadoranomina.data.Mappers.NoveltyMapper
import com.magotecnologia.calculadoranomina.data.PaySlipDatabase
import com.magotecnologia.calculadoranomina.domain.Novelty

/**
 * Created by Marco-Laptop on 24/11/2019.
 * Repositorio que maneja las novedades
 */


class NoveltyRepository(application: Application) {

    private val noveltyDao =
        PaySlipDatabase.getInstance(application.applicationContext).noveltyDao

    fun getAllNoveltiesLive(): LiveData<List<Novelty>> = NoveltyMapper().entityLiveDataToDomain(
        noveltyDao.getOrderedNoveltyListLive()
    )

    suspend fun getAllNovelties(): List<Novelty> =
        NoveltyMapper().entityListToDomain((noveltyDao.getOrderedNoveltyList()))

    suspend fun saveNovelty(novelty: Novelty) =
        noveltyDao.insert(NoveltyMapper().domainToEntity(novelty))

    suspend fun getNoveltiesByMonth(month: Int) =
        NoveltyMapper().entityListToDomain(noveltyDao.getNoveltiesByMonth(month))

    suspend fun getNoveltiesByEmployee(employeeId: Int) =
        NoveltyMapper().entityListToDomain(noveltyDao.getNoveltiesByEmployee(employeeId))

    suspend fun getNoveltiesByEmployeeAndMonth(employeeId: Int, month: Int): List<Novelty> =
        NoveltyMapper().entityListToDomain(
            noveltyDao.getNoveltiesByEmployeeAndMonth(
                employeeId,
                month
            )
        )

}