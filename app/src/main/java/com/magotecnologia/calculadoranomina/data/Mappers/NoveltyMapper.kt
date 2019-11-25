package com.magotecnologia.calculadoranomina.data.Mappers

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.magotecnologia.calculadoranomina.data.Entities.NoveltyEntity
import com.magotecnologia.calculadoranomina.domain.Employee
import com.magotecnologia.calculadoranomina.domain.Novelty
import com.magotecnologia.calculadoranomina.domain.NoveltyType
import java.util.*

/**
 * Created by Marco-Laptop on 24/11/2019.
 * Clase que convierte de un DTO a un objeto del dominio
 */


class NoveltyMapper {

    fun domainToEntity(novelty: Novelty): NoveltyEntity {
        val entity = NoveltyEntity(
            employeeId = novelty.employee.id,
            date = novelty.reportDate?.time,
            type = novelty.type.ordinal,
            value = novelty.value,
            month = novelty.month
        )
        entity.Id = novelty.id
        return entity
    }

    fun entityToDomain(noveltyEntity: NoveltyEntity) = Novelty(
        id = noveltyEntity.Id,
        employee = Employee(id = noveltyEntity.Id),
        type = NoveltyType.values()[noveltyEntity.type],
        value = noveltyEntity.value,
        month = noveltyEntity.month,
        reportDate = if (noveltyEntity.date != null) Date(noveltyEntity.date) else null
    )


    fun entityListToDomain(entities: List<NoveltyEntity>): List<Novelty> =
        entities.map { noveltyEntity ->
            entityToDomain(noveltyEntity)
        }

    fun entityLiveDataToDomain(entityList: LiveData<List<NoveltyEntity>>) =
        Transformations.map(entityList) { entityListToDomain(it) }

}
