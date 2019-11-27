package com.magotecnologia.calculadoranomina.domain

/**
 * Created by Marco-Laptop on 25/11/2019.
 * Contiene las ganancias
 */


class Earns(
    val basicSalary: Int,
    val allowance: Int,
    val extraTime: Int,
    val bonus: Int,
    val commissions: Int
) {
    var total = 0
    fun calculateTotal() {
        total = basicSalary + allowance + extraTime + bonus + commissions
    }
}