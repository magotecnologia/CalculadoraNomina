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
    var novelties = mutableListOf<Novelty>()

    fun hasTransportationAllowance(): Boolean =
        (this.salary <= (Constants.MINIMUM_SALARY * 2))

    fun getEarns(): Earns {
        val extraDay =
            novelties.filter { it.type == NoveltyType.HORAS_EXTRAS_DIURNAS }.sumBy { it.value }
        val extraNight =
            novelties.filter { it.type == NoveltyType.HORAS_EXTRAS_NOCTURNAS }.sumBy { it.value }
        val dominicalextraDay =
            novelties.filter { it.type == NoveltyType.HORAS_EXTRAS_DIURNAS_FESTIVA }
                .sumBy { it.value }
        val dominicalextraNight =
            novelties.filter { it.type == NoveltyType.HORAS_EXTRAS_NOCTURNAS_FESTIVA }
                .sumBy { it.value }
        val dominicalCharge =
            novelties.filter { it.type == NoveltyType.HORAS_FESTIVA }.sumBy { it.value }
        val totalExtra = ExtraTimeCalculator(this.salary).calculateTotal(
            extraDay,
            extraNight,
            dominicalextraDay,
            dominicalextraNight,
            dominicalCharge
        )
        val bonus = novelties.filter { it.type == NoveltyType.BONIFICACIONES }.sumBy { it.value }
        val commisions = novelties.filter { it.type == NoveltyType.COMISIONES }.sumBy { it.value }
        val earns = Earns(
            basicSalary = this.salary,
            allowance = if (hasTransportationAllowance()) Constants.TRANSPORTATION_ALLOWANCE else 0,
            extraTime = totalExtra,
            bonus = bonus,
            commissions = commisions
        )
        earns.calculateTotal()
        return earns
    }

    fun getReductions(): Reduction {
        val earns = getEarns()
        val parafiscalCalculator =
            ParafiscalCalculator(totalSalary = earns.basicSalary + earns.extraTime)
        val loans = novelties.filter { it.type == NoveltyType.PRESTAMOS }.sumBy { it.value }
        val foreClosures =
            novelties.filter { it.type == NoveltyType.EMBARGO_JUDICIAL || it.type == NoveltyType.EMBARGO_LEGAL }
                .sumBy { it.value }
        val employeeFund =
            novelties.filter { it.type == NoveltyType.FONDO_DE_EMPLEADOS }.sumBy { it.value }
        val reduction = Reduction(
            health = parafiscalCalculator.calculateEmployeeHealthReduction(),
            pension = parafiscalCalculator.calculateEmployeePensionReduction(), loan = loans,
            foreClosure = foreClosures, employeeFund = employeeFund
        )
        reduction.calculateTotal()
        return reduction
    }

    fun getCompanyReduction(): CompanyContribution {
        val earns = this.getEarns()
        val totalSalary = earns.extraTime.plus(earns.commissions).plus(this.salary)
        return CompanyContribution(
            health = if (totalSalary > 10 * Constants.MINIMUM_SALARY)
                (totalSalary * Constants.COMPANY_HEALTH_PERCENTAGE / 100).toInt() else 0,
            pension = (totalSalary * Constants.COMPANY_PENSION_PERCENTAGE / 100),
            professionalInsurance = (totalSalary * Constants.COMPANY_INSURANCE_PERCENTAGE / 100),
            familyCompensation = totalSalary * Constants.COMPANY_FAMILY_COMPENSATION_PERCENTAGE / 100,
            familyBienestar = if (totalSalary > 10 * Constants.MINIMUM_SALARY)
                totalSalary * Constants.COMPANY_FAMILY_BIENESTAR_PERCENTAGE / 100 else 0,
            Sena = if (totalSalary > 10 * Constants.MINIMUM_SALARY)
                totalSalary * Constants.COMPANY_SENA_PERCENTAGE / 100 else 0
        )
    }

    fun getSocialBenefits(): SocialBenefits {
        val earns = this.getEarns()
        val mixedSalary =
            this.salary.plus(earns.extraTime).plus(earns.commissions).plus(earns.allowance)
        return SocialBenefits(
            layoffs = (mixedSalary * Constants.LAYOFF_PERCENTAGE / 100).toInt(),
            layoffsInterest = (mixedSalary * Constants.LAYOFF_PERCENTAGE * Constants.LAYOFF_INTEREST_PERCENTAGE / 10000).toInt(),
            socialBonus = (mixedSalary * Constants.SOCIAL_BONUS_PERCENTAGE / 100).toInt(),
            vacations = (mixedSalary * Constants.VACATIONS_PERCENTAGE / 100).toInt()
        )
    }
}
