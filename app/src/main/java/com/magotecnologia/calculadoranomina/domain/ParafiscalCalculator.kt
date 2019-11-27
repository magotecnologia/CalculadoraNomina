package com.magotecnologia.calculadoranomina.domain

/**
 * Created by Marco-Laptop on 25/11/2019.
 * Calcula los aportes parafiscales
 */


class ParafiscalCalculator(val totalSalary: Int) {

    fun calculateEmployeeHealthReduction() = (totalSalary * HEALTH_EMPLOYEE_PERCENTAGE) / 100
    fun calculateEmployeePensionReduction() = (totalSalary * PENSION_EMPLOYEE_PERCENTAGE) / 100


    companion object {
        const val HEALTH_EMPLOYEE_PERCENTAGE = 4
        const val PENSION_EMPLOYEE_PERCENTAGE = 4
    }
}