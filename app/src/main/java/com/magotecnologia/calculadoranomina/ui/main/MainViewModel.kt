package com.magotecnologia.calculadoranomina.ui.main

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.magotecnologia.calculadoranomina.data.repository.EmployeeRepository
import com.magotecnologia.calculadoranomina.domain.Employee
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = EmployeeRepository(application)

    private var _employeeList = MutableLiveData<List<Employee>>().apply { value = emptyList() }
    var employeeList: LiveData<List<Employee>> = _employeeList

    fun getAllEmployees() {
        viewModelScope.launch {
            _employeeList.postValue(repository.getAllEmployees())
        }
    }

}
