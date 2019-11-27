package com.magotecnologia.calculadoranomina.domain

/**
 * Created by Marco-Laptop on 25/11/2019.
 * Herramienta para calcular las horas extras
 */


class ExtraTimeCalculator(val salary: Int) {
    private var totalExtraTime: Double = 0.toDouble()

    fun calculateTotal(
        extraDayHours: Int, extraNightHours: Int, extraDominicalDayHours: Int,
        extraDominicalNightHours: Int, dominicalChargeHours: Int
    ): Int {
        totalExtraTime += calculateDayExtraTime(extraDayHours)
        totalExtraTime += calculateNightExtraTime(extraNightHours)
        totalExtraTime += calculateDominicalDayExtraTime(extraDominicalDayHours)
        totalExtraTime += calculateDominicalNightExtraTime(extraDominicalNightHours)
        totalExtraTime += calculateDominicalTime(dominicalChargeHours)
        return totalExtraTime.toInt()
    }

    private fun calculateDominicalTime(hours: Int) =
        (salary * hours * DOMINICAL_EXTRA_TIME_MULTIPLIER) / 240

    private fun calculateDayExtraTime(hours: Int) =
        (salary * hours * DAY_EXTRA_TIME_MULTIPLIER) / 240

    private fun calculateNightExtraTime(hours: Int) =
        (salary * hours * NIGHT_EXTRA_TIME_MULTIPLIER) / 240

    private fun calculateDominicalDayExtraTime(hours: Int) =
        (salary * hours * DOMINICAL_DAY_EXTRA_TIME_MULTIPLIER) / 240

    private fun calculateDominicalNightExtraTime(hours: Int) =
        (salary * hours * DOMINICAL_NIGHT_EXTRA_TIME_MULTIPLIER) / 240

    companion object {
        const val DAY_EXTRA_TIME_MULTIPLIER = 1.25
        const val NIGHT_EXTRA_TIME_MULTIPLIER = 1.75
        const val DOMINICAL_DAY_EXTRA_TIME_MULTIPLIER = 2
        const val DOMINICAL_NIGHT_EXTRA_TIME_MULTIPLIER = 2.5
        const val DOMINICAL_EXTRA_TIME_MULTIPLIER = 1.75
    }

}