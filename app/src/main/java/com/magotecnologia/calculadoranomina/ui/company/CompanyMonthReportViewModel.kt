package com.magotecnologia.calculadoranomina.ui.company

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.magotecnologia.calculadoranomina.data.repository.EmployeeRepository
import com.magotecnologia.calculadoranomina.domain.Bill
import com.magotecnologia.calculadoranomina.domain.Earns
import com.magotecnologia.calculadoranomina.domain.Employee
import com.magotecnologia.calculadoranomina.domain.Reduction
import kotlinx.coroutines.launch

class CompanyMonthReportViewModel(application: Application) : AndroidViewModel(application) {
    private val employeeRepository = EmployeeRepository(application)
    private val _billingDetails = MutableLiveData<Bill>()
    val billingDetails: LiveData<Bill> = _billingDetails

    fun getCompanyData() {
        viewModelScope.launch {
            val employee: List<Employee?> = employeeRepository.getAllEmploteeFull()
            employee.let {
                val earns = it.map { employee -> employee?.getEarns() }
                val reductions = it.map { employee -> employee?.getReductions() }
                if (earns.isNotEmpty() && reductions.isNotEmpty()) {
                    val totalEarns: Earns? = earns.reduce(operation = { previous, element: Earns? ->
                        Earns(
                            basicSalary = previous!!.basicSalary.plus(element!!.basicSalary),
                            allowance = previous.allowance.plus(element.allowance),
                            extraTime = previous.extraTime.plus(element.extraTime),
                            bonus = previous.bonus.plus(element.bonus),
                            commissions = previous.commissions.plus(element.commissions)
                        )

                    })
                    val totalReductions: Reduction? =
                        reductions.reduce(operation = { previous, element: Reduction? ->

                            Reduction(
                                health = previous!!.health.plus(element!!.health),
                                pension = previous.pension.plus(element.pension),
                                loan = previous.loan.plus(element.loan),
                                foreClosure = previous.foreClosure.plus(element.foreClosure),
                                employeeFund = previous.employeeFund.plus(element.employeeFund)
                            )
                        })

                    totalEarns?.let { earns ->
                        totalReductions?.let { reductions ->
                            earns.calculateTotal()
                            reductions.calculateTotal()
                            val actualBill = Bill(earns, reductions)
                            _billingDetails.postValue(actualBill)
                        }
                    }
                }

            }

        }
    }
}
