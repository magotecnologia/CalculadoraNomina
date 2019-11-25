package com.magotecnologia.calculadoranomina.data.Mappers

import androidx.room.Embedded
import androidx.room.Relation
import com.magotecnologia.calculadoranomina.data.Entities.EmployeeEntity
import com.magotecnologia.calculadoranomina.data.Entities.NoveltyEntity


/**
 * Created by Marco-Laptop on 24/11/2019.
 * Clase que contiene al empleado con sus novedades
 */


class EmployeeAndNovelties {
    @Embedded
    var employee: EmployeeEntity? = null

    @Relation(
        parentColumn = EmployeeEntity.ID_COLUMN_NAME,
        entityColumn = NoveltyEntity.EMPLOYEE_COLUMN_NAME,
        entity = NoveltyEntity::class
    )
    var novelties: List<NoveltyEntity>? = null
}