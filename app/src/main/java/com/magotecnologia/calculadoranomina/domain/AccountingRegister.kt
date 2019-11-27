package com.magotecnologia.calculadoranomina.domain

/**
 * Created by Marco-Laptop on 26/11/2019.
 * Generador de Datos del PUC
 */


class AccountingRegister(val employee: Employee) {

    fun getRegister(): List<AccountingDetail> {
        val register = mutableListOf<AccountingDetail>()
        val earns = employee.getEarns()
        val downs = employee.getReductions()
        val reduction = employee.getCompanyReduction()
        val socialBenefits = employee.getSocialBenefits()
        val personalOutlay = mutableListOf<AccountingDetail>()
        val salary = AccountingDetail(
            code = 510506, detail = "Sueldos", value = employee.salary,
            isCredit = false, level = 2
        )
        personalOutlay.add(salary)
        val extraTime = AccountingDetail(
            code = 510515, detail = "Horas extras y recargos", value = earns.extraTime,
            isCredit = false, level = 2
        )
        personalOutlay.add(extraTime)
        val commissions = AccountingDetail(
            code = 510518, detail = "Comisiones", value = earns.commissions,
            isCredit = false, level = 2
        )
        personalOutlay.add(commissions)
        val allowance = AccountingDetail(
            code = 510527, detail = "Auxilio de Transporte", value = earns.allowance,
            isCredit = false, level = 2
        )
        personalOutlay.add(allowance)
        val layoff = AccountingDetail(
            code = 510530, detail = "Cesantias", value = socialBenefits.layoffs,
            isCredit = false, level = 2
        )
        personalOutlay.add(layoff)
        val layoffInterest = AccountingDetail(
            code = 510533,
            detail = "Intereses sobre Cesantias",
            value = socialBenefits.layoffsInterest,
            isCredit = false,
            level = 2
        )
        personalOutlay.add(layoffInterest)
        val socialBonus = AccountingDetail(
            code = 510536, detail = "Prima de Servicios", value = socialBenefits.socialBonus,
            isCredit = false, level = 2
        )
        personalOutlay.add(socialBonus)
        val vacations = AccountingDetail(
            code = 510539, detail = "Vacaciones", value = socialBenefits.vacations,
            isCredit = false, level = 2
        )
        personalOutlay.add(vacations)
        val bonus = AccountingDetail(
            code = 510548, detail = "Bonificaciones", value = earns.bonus,
            isCredit = false, level = 2
        )
        personalOutlay.add(bonus)
        val arl = AccountingDetail(
            code = 510568, detail = "Aportes ARL", value = reduction.professionalInsurance,
            isCredit = false, level = 2
        )
        personalOutlay.add(arl)
        val eps = AccountingDetail(
            code = 510569, detail = "Aportes EPS", value = reduction.health,
            isCredit = false, level = 2
        )
        personalOutlay.add(eps)
        val contribuionPension = AccountingDetail(
            code = 510570, detail = "Aportes pensiones", value = reduction.pension,
            isCredit = false, level = 2
        )
        personalOutlay.add(contribuionPension)
        val familyCompensation = AccountingDetail(
            code = 510572,
            detail = "Aportes caja de compensacion ",
            value = reduction.familyCompensation,
            isCredit = false,
            level = 2
        )
        personalOutlay.add(familyCompensation)
        val icbf = AccountingDetail(
            code = 510575, detail = "Aportes ICBF", value = reduction.familyBienestar,
            isCredit = false, level = 2
        )
        personalOutlay.add(icbf)
        val sena = AccountingDetail(
            code = 510578, detail = "Aportes SENA", value = reduction.Sena,
            isCredit = false, level = 2
        )
        personalOutlay.add(sena)
        val personalOutlayDetail = AccountingDetail(
            code = 5105, detail = "Gastos de personal", value = 0,
            isCredit = false, level = 1, subDetails = personalOutlay
        )
        val personalOutlayTotalValue = personalOutlay.reduce(operation = { previous, element ->
            AccountingDetail(
                code = 0, detail = "", value = previous.value.plus(element.value),
                isCredit = false, level = 0
            )
        }).value
        val finalPersonalOutlayDetail = personalOutlayDetail.copy(value = personalOutlayTotalValue)
        register.add(finalPersonalOutlayDetail)
        val housing = AccountingDetail(
            code = 136505, detail = "Vivienda", value = downs.loan,
            isCredit = true, level = 2
        )
        val cxc = AccountingDetail(
            code = 1365, detail = "Cuentas por Cobrar a trabajadores", value = downs.loan,
            isCredit = true, level = 1, subDetails = listOf(housing)
        )
        register.add(cxc)
        val retentions = mutableListOf<AccountingDetail>()
        val epsContribution = AccountingDetail(
            code = 237005, detail = "Aportes EPS", value = downs.health.plus(reduction.health),
            isCredit = true, level = 2
        )
        retentions.add(epsContribution)
        val arlContribution = AccountingDetail(
            code = 237006, detail = "Aportes ARL", value = (reduction.professionalInsurance),
            isCredit = true, level = 2
        )
        retentions.add(arlContribution)
        val othersContribution = AccountingDetail(
            code = 237010,
            detail = "Aportes ICBF, SENA y cajas ",
            value = reduction.familyBienestar.plus(reduction.Sena).plus(reduction.familyCompensation),
            isCredit = true,
            level = 2
        )
        retentions.add(othersContribution)
        val judicialForeClosure = AccountingDetail(
            code = 237025, detail = "Embargos Judiciales", value = downs.foreClosure,
            isCredit = true, level = 2
        )
        retentions.add(judicialForeClosure)
        val employeeFund = AccountingDetail(
            code = 237045, detail = "Fondo de Empleados", value = downs.employeeFund,
            isCredit = true, level = 2
        )
        retentions.add(employeeFund)
        val retentionOutlayDetail = AccountingDetail(
            code = 2370, detail = "Retenciones y aportes de nomina", value = 0,
            isCredit = true, level = 1, subDetails = retentions
        )
        val retentionOutlayTotalValue = retentions.reduce(operation = { previous, element ->
            AccountingDetail(
                code = 0, detail = "", value = previous.value.plus(element.value),
                isCredit = false, level = 0
            )
        }).value
        val finalRetentionOutlayDetail =
            retentionOutlayDetail.copy(value = retentionOutlayTotalValue)
        register.add(finalRetentionOutlayDetail)
        val pension = AccountingDetail(
            code = 238030,
            detail = "Aportes fondo de pensiones",
            value = downs.pension.plus(reduction.pension),
            isCredit = true,
            level = 2
        )
        val creditors = AccountingDetail(
            code = 2380,
            detail = "Acreedores varios",
            value = downs.pension.plus(reduction.pension),
            isCredit = true,
            level = 1,
            subDetails = listOf(pension)
        )
        register.add(creditors)
        val obligations = mutableListOf<AccountingDetail>()
        val layoffObligation = AccountingDetail(
            code = 261005, detail = "Cesantias", value = socialBenefits.layoffs,
            isCredit = true, level = 2
        )
        obligations.add(layoffObligation)
        val layoffInterestObligation = AccountingDetail(
            code = 261010,
            detail = "Intereses sobre Cesantias",
            value = socialBenefits.layoffsInterest,
            isCredit = true,
            level = 2
        )
        obligations.add(layoffInterestObligation)
        val vacationsObligation = AccountingDetail(
            code = 261015, detail = "Vacaciones", value = socialBenefits.vacations,
            isCredit = true, level = 2
        )
        obligations.add(vacationsObligation)
        val socialBonusObligation = AccountingDetail(
            code = 261020, detail = "Prima de Servicios", value = socialBenefits.socialBonus,
            isCredit = true, level = 2
        )
        obligations.add(socialBonusObligation)
        val obligationsDetail = AccountingDetail(
            code = 2610, detail = "Para Obligaciones laborales", value = 0,
            isCredit = true, level = 1, subDetails = obligations
        )
        val obligationsTotalValue = obligations.reduce(operation = { previous, element ->
            AccountingDetail(
                code = 0, detail = "", value = previous.value.plus(element.value),
                isCredit = false, level = 0
            )
        }).value
        val finalObligationsDetail = obligationsDetail.copy(value = obligationsTotalValue)
        register.add(finalObligationsDetail)
        val remainingValue = register.reduce(operation = { previous, element ->
            AccountingDetail(
                code = 0,
                detail = "",
                value = if (element.isCredit == false) previous.value.plus(element.value) else previous.value.minus(
                    element.value
                ),
                isCredit = false,
                level = 0
            )
        }).value
        val salaryByPay = AccountingDetail(
            code = 2505, detail = "Salarios Por pagar", value = remainingValue,
            isCredit = true, level = 1
        )
        register.add(salaryByPay)
        val debitValue =
            register.filter { it.isCredit == false }.reduce(operation = { previous, element ->
                AccountingDetail(
                    code = 0, detail = "", value = previous.value.plus(element.value),
                    isCredit = false, level = 0
                )
            }).value
        val creditValue =
            register.filter { it.isCredit == true }.reduce(operation = { previous, element ->
                AccountingDetail(
                    code = 0, detail = "", value = previous.value.plus(element.value),
                    isCredit = false, level = 0
                )
            }).value
        val totals = AccountingDetail(
            code = 0, detail = "Total", value = debitValue,
            isCredit = null, level = 1
        )
        if (debitValue == creditValue) {
            register.add(totals)
        }
        return register
    }

}