package com.magotecnologia.calculadoranomina.domain

/**
 * Created by Marco-Laptop on 24/11/2019.
 * Tipos de novedades de nomina
 */
enum class NoveltyType(val valueToShow: String, val measureUnit: String) {
    HORAS_EXTRAS_DIURNAS("Horas Extras diurnas", "Horas"),
    HORAS_EXTRAS_NOCTURNAS("Horas Extras nocturna", "Horas"),
    HORAS_EXTRAS_DIURNAS_FESTIVA("Horas Extras Diurnas Festiva", "Horas"),
    HORAS_EXTRAS_NOCTURNAS_FESTIVA("Horas Extras Nocturna Festivas", "Horas"),
    HORAS_FESTIVA("Horas Festivas", "Horas"),
    BONIFICACIONES("Bonificaciones", "Pesos"),
    COMISIONES("Comisiones", "Pesos"),
    PRESTAMOS("Prestamos", "Pesos"),
    EMBARGO_LEGAL("Embargo Legal", "Pesos"),
    EMBARGO_JUDICIAL("Embargo Judicial", "Pesos"),
    FONDO_DE_EMPLEADOS("Fondo de Empleados", "Pesos")
}