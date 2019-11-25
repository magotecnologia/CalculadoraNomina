package com.magotecnologia.calculadoranomina.domain

import java.util.*

/**
 * Created by Marco-Laptop on 24/11/2019.
 * Clase que representa una novedad asignada
 */


class Novelty(
    val id: Int = 0,
    val type: NoveltyType,
    val employee: Employee,
    val value: Int,
    val month: Int,
    val reportDate: Date?
) {
}