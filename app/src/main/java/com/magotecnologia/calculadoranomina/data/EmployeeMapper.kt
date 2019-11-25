package com.magotecnologia.calculadoranomina.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.magotecnologia.calculadoranomina.data.Entities.EmployeeEntity
import com.magotecnologia.calculadoranomina.domain.Employee

/**
 * Created by Marco-Laptop on 24/11/2019.
 * Clase que convierte de un DTO a un objeto del dominio
 */


class EmployeeMapper {
    fun DomainToEntity(employee: Employee): EmployeeEntity {
        val entity =
            EmployeeEntity(
                Dni = employee.Dni,
                firstName = employee.firstName,
                lastName = employee.lastName,
                position = employee.position,
                photoPath = employee.photoPath,
                salary = employee.salary,
                phoneNumber = employee.phoneNumber
            )
        entity.Id = employee.id
        return entity
    }

    fun EntityToDomain(employeeEntity: EmployeeEntity) =
        Employee(
            Dni = employeeEntity.Dni,
            firstName = employeeEntity.firstName,
            lastName = employeeEntity.lastName,
            position = employeeEntity.position,
            salary = employeeEntity.salary,
            phoneNumber = employeeEntity.phoneNumber,
            id = employeeEntity.Id,
            photoPath = employeeEntity.photoPath
        )

    fun ListEntityToDomain(entityList: List<EmployeeEntity>) =
        entityList.map { employee ->
            EntityToDomain(employee)
        }

    fun LiveListEntityToDomain(entityList: LiveData<List<EmployeeEntity>>) =
        Transformations.map(entityList) { ListEntityToDomain(it) }

}
