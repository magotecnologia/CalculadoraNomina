package com.magotecnologia.calculadoranomina.data.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

/**
 * Created by Marco-Laptop on 24/11/2019.
 * Entidad contenedora de los datos del empleado
 */

@Entity(tableName = EmployeeEntity.TABLE_NAME)
data class EmployeeEntity(

    @NotNull
    @ColumnInfo(name = DNI_COLUMN_NAME)
    val Dni: Int = 0,

    @NotNull
    @ColumnInfo(name = FIRST_NAME_COLUMN_NAME)
    val firstName: String? = null,
    @ColumnInfo(name = LAST_NAME_COLUMN_NAME)
    @NotNull
    val lastName: String? = null,

    @NotNull
    @ColumnInfo(name = POSITION_COLUMN_NAME)
    val position: String,

    @NotNull
    @ColumnInfo(name = SALARY_COLUMN_NAME)
    val salary: Int = 0,

    @ColumnInfo(name = PHONE_COLUMN_NAME)
    val phoneNumber: String? = null
) {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID_COLUMN_NAME)
    var Id: Int = 0

    companion object {
        const val TABLE_NAME = "Employee"
        const val ID_COLUMN_NAME = "ID"
        const val DNI_COLUMN_NAME = "IDENTIFICATION_NUMBER"
        const val FIRST_NAME_COLUMN_NAME = "FIRST_NAME"
        const val LAST_NAME_COLUMN_NAME = "LAST_NAME"
        const val PHONE_COLUMN_NAME = "PHONE_NUMBER"
        const val POSITION_COLUMN_NAME = "POSITION"
        const val SALARY_COLUMN_NAME = "SALARY"
    }
}