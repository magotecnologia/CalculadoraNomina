package com.magotecnologia.calculadoranomina.ui.employee

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.magotecnologia.calculadoranomina.data.repository.EmployeeRepository
import com.magotecnologia.calculadoranomina.domain.Bill
import com.magotecnologia.calculadoranomina.domain.Employee
import kotlinx.coroutines.launch


/**
 * Created by Marco-Laptop on 11/11/2019.
 */


class EmployeeMonthReportViewModel(application: Application) : AndroidViewModel(application) {
    private val employeeRepository = EmployeeRepository(application)
    private val _billingDetails = MutableLiveData<Bill>()
    val billingDetails: LiveData<Bill> = _billingDetails

    fun getEmployeeData(employeeId: Int) {
        viewModelScope.launch {
            val employee: Employee? = employeeRepository.getEmployeeFullByIdAndMonth(employeeId)
            employee?.let {
                val actualBill = Bill(it.getEarns(), it.getReductions())
                _billingDetails.postValue(actualBill)
            }

        }
    }

}