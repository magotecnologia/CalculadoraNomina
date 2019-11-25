package com.magotecnologia.calculadoranomina.data.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

/**
 * Created by Marco-Laptop on 24/11/2019.
 * Entidad que guarda los datos de las novedades
 */

@Entity(
    tableName = NoveltyEntity.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = EmployeeEntity::class,
            parentColumns = [EmployeeEntity.ID_COLUMN_NAME],
            childColumns = [NoveltyEntity.EMPLOYEE_COLUMN_NAME]
        )]
)
data class NoveltyEntity(
    @NotNull
    @ColumnInfo(name = EMPLOYEE_COLUMN_NAME)
    val employeeId: Int = 0,

    @ColumnInfo(name = DATE_COLUMN_NAME)
    val date: Long? = null,
    @NotNull
    @ColumnInfo(name = TYPE_COLUMN_NAME)
    val type: Int = 0,

    @NotNull
    @ColumnInfo(name = VALUE_COLUMN_NAME)
    val value: Int = 0,

    @NotNull
    @ColumnInfo(name = MONTH_COLUMN_NAME)
    val month: Int = 0
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID_COLUMN_NAME)
    var Id: Int = 0

    companion object {
        const val TABLE_NAME = "NOVELTY"
        const val ID_COLUMN_NAME = "ID"
        const val EMPLOYEE_COLUMN_NAME = "EMPLOYEE_ID"
        const val DATE_COLUMN_NAME = "DATE"
        const val TYPE_COLUMN_NAME = "TYPE"
        const val VALUE_COLUMN_NAME = "VALUE"
        const val MONTH_COLUMN_NAME = "MONTH"
    }
}
