package com.magotecnologia.calculadoranomina.data

import androidx.room.TypeConverter
import java.util.*

/**
 * Created by Marco-Laptop on 22/11/2019.
 */


class DateConverter {
    @TypeConverter
    fun toDate(value: Long?) = if (value == null) null else Date(value)

    @TypeConverter
    fun toLong(value: Date) = value.time

}