package com.magotecnologia.calculadoranomina.domain

/**
 * Created by Marco-Laptop on 24/11/2019.
 * Clase que contiene la logica de los empleados
 */


class Employee(
    val Dni: Int = 0,
    val firstName: String? = null,
    val lastName: String? = null,
    var position: String = "",
    val salary: Int = 0,
    val phoneNumber: String? = null,
    val id: Int = 0,
    val photoPath: String? = null
) {

}
