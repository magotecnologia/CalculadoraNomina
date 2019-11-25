package com.magotecnologia.calculadoranomina.ui.employee

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.magotecnologia.calculadoranomina.data.repository.EmployeeRepository
import com.magotecnologia.calculadoranomina.domain.Employee
import kotlinx.coroutines.launch

class EmployeeViewModel(application: Application) : AndroidViewModel(application) {


    private val repository = EmployeeRepository(application)
    private val _employeeData = MutableLiveData<Employee?>().apply { value = null }
    val employeeData: LiveData<Employee?> = _employeeData

    fun getEmployeeData(employeeId: Int) {
        viewModelScope.launch {
            _employeeData.postValue(repository.getEmployeeById(employeeId))
        }
    }

}
