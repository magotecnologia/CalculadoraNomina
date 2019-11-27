package com.magotecnologia.calculadoranomina.ui.accounting

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.magotecnologia.calculadoranomina.data.repository.EmployeeRepository
import com.magotecnologia.calculadoranomina.domain.AccountingDetail
import com.magotecnologia.calculadoranomina.domain.AccountingRegister
import com.magotecnologia.calculadoranomina.domain.Employee
import kotlinx.coroutines.launch

class RegisterViewModel(application: Application) : AndroidViewModel(application) {
    private val employeeRepository = EmployeeRepository(application)
    private val _billingDetails = MutableLiveData<List<AccountingDetail>>()
    val billingDetails: LiveData<List<AccountingDetail>> = _billingDetails

    fun getData() {
        viewModelScope.launch {
            val employees: List<Employee?> = employeeRepository.getAllEmploteeFull()
            val registers = employees.filterNotNull().map { AccountingRegister(it).getRegister() }
            val total = registers.reduce(operation = { previous, element ->
                sum(previous, element)
            })
            _billingDetails.postValue(total)
        }
    }

    fun sum(
        previous: List<AccountingDetail>,
        next: List<AccountingDetail>
    ): List<AccountingDetail> {
        val zippedList = mutableListOf<AccountingDetail>()
        for (first in previous) {
            for (second in next) {
                if (first.code == second.code) {
                    val value = first.value + second.value
                    var updated = first.copy(value = value)
                    if (first.subDetails.size > 0) {
                        val inner = sum(first.subDetails, second.subDetails)
                        updated = updated.copy(subDetails = inner)
                    }
                    zippedList.add(updated)
                }
            }
        }
        return zippedList
    }
}
