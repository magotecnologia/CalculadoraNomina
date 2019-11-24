package com.magotecnologia.calculadoranomina.model

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import com.magotecnologia.calculadoranomina.data.Daos.EmployeeDao
import com.magotecnologia.calculadoranomina.data.Entities.EmployeeEntity
import com.magotecnologia.calculadoranomina.data.PaySlipDatabase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

/**
 * Created by Marco-Laptop on 24/11/2019.
 */
@RunWith(JUnit4::class)
class PaySlipDatabaseTest {
    private lateinit var employeeDao: EmployeeDao
    private lateinit var db: PaySlipDatabase
    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation()
            .targetContext // Using an in-memory database because the information stored here disappears when the // process is killed
        db = Room.inMemoryDatabaseBuilder(
            context,
            PaySlipDatabase::class.java
        )
//                Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        employeeDao = db.employeeDao
    }

    @Test
    fun insertAndGetEmployee() {
        val engineerEntity = EmployeeEntity(position = "Ingeniero", Dni = 123)
        employeeDao.insert(engineerEntity)
        val result = employeeDao.getEmployeeByDNI(123)
        assertEquals(result.position, engineerEntity.position)
    }
}