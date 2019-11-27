package com.magotecnologia.calculadoranomina.domain

/**
 * Created by Marco-Laptop on 25/11/2019.
 * Clase que contiene las reducciones del comprobante
 */


class Reduction(
    val health: Int = 0,
    val pension: Int = 0,
    val loan: Int = 0,
    val foreClosure: Int = 0,
    val employeeFund: Int = 0
) {
    var total = 0
    fun calculateTotal() {
        total = health + pension + loan + foreClosure + employeeFund
    }
}