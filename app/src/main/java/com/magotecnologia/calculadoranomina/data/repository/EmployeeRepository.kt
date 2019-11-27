package com.magotecnologia.calculadoranomina.data.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.magotecnologia.calculadoranomina.data.Mappers.EmployeeMapper
import com.magotecnologia.calculadoranomina.data.PaySlipDatabase
import com.magotecnologia.calculadoranomina.domain.Employee

/**
 * Created by Marco-Laptop on 24/11/2019.
 * Repositorio de los datos de empleados
 */


class EmployeeRepository(application: Application) {
    private val employeeDao =
        PaySlipDatabase.getInstance(application.applicationContext).employeeDao

    fun getAllEmployeesLive(): LiveData<List<Employee>> =
        EmployeeMapper().entityLiveDataToDomain(employeeDao.getOrderedEmployeeListLive())

    suspend fun getAllEmployees(): List<Employee> =
        EmployeeMapper().entityListToDomain(employeeDao.getOrderedEmployeeList())

    suspend fun saveEmployee(employee: Employee) =
        employeeDao.insert(EmployeeMapper().domainToEntity(employee))

    suspend fun getEmployeeByDNI(employeeId: Int) = employeeDao.getEmployeeByDNI(employeeId)
    suspend fun getEmployeeById(employeeId: Int) =
        EmployeeMapper().entityToDomain(employeeDao.getEmployeeById(employeeId))

    suspend fun getEmployeeFullByIdAndMonth(employeeId: Int): Employee? =
        EmployeeMapper().employeeAndNoveltiesToDomain(
            employeeDao.getEmployeeAndNoveltiesById(employeeId)
        )

    suspend fun getAllEmploteeFull(): List<Employee?> =
        EmployeeMapper().employeeAndNoveltiesListToDomain(employeeDao.getAllEmployeeAndNovelties())
}