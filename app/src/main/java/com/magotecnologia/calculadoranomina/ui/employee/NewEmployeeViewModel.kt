package com.magotecnologia.calculadoranomina.ui.employee

import android.app.Application
import androidx.lifecycle.*
import com.magotecnologia.calculadoranomina.data.repository.EmployeeRepository
import com.magotecnologia.calculadoranomina.domain.Employee
import kotlinx.coroutines.launch

class NewEmployeeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = EmployeeRepository(application)
    private val successFull = MutableLiveData<Boolean>()
    val successFullMessage: LiveData<String> =
        Transformations.map(successFull) { if (it) "EXITO" else "" }

    fun saveNewEmployee(employeeToCreate: Employee) {
        viewModelScope.launch {
            repository.saveEmployee(employeeToCreate)
            successFull.postValue(true)
        }
    }
}
