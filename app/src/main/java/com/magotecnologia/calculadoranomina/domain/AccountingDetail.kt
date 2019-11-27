package com.magotecnologia.calculadoranomina.domain

/**
 * Created by Marco-Laptop on 26/11/2019.
 * Detalle de Contabilidad
 */


data class AccountingDetail(
    val code: Int, val detail: String, val isCredit: Boolean?, val level: Int,
    val value: Int, val subDetails: List<AccountingDetail> = emptyList()
) {
}