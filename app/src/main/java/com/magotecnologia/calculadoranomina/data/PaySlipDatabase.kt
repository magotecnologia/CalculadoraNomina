package com.magotecnologia.calculadoranomina.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.magotecnologia.calculadoranomina.data.Daos.EmployeeDao
import com.magotecnologia.calculadoranomina.data.Entities.EmployeeEntity

/**
 * Created by Marco-Laptop on 22/11/2019.
 */

@Database(entities = [EmployeeEntity::class], exportSchema = true, version = 1)
@TypeConverters(DateConverter::class)
abstract class PaySlipDatabase : RoomDatabase() {
    abstract val employeeDao: EmployeeDao

    companion object {

        private const val DATABASE_NAME = "PAYSLIP"
        @Volatile
        private var INSTANCE: PaySlipDatabase? = null

        fun getInstance(context: Context): PaySlipDatabase {
            synchronized(this) {
                var instance =
                    INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PaySlipDatabase::class.java,
                        DATABASE_NAME
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}