package com.magotecnologia.calculadoranomina.ui.novelty

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.magotecnologia.calculadoranomina.data.repository.EmployeeRepository
import com.magotecnologia.calculadoranomina.data.repository.NoveltyRepository
import com.magotecnologia.calculadoranomina.domain.Employee
import com.magotecnologia.calculadoranomina.domain.Novelty
import com.magotecnologia.calculadoranomina.domain.NoveltyType
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.*

/**
 * Created by Marco-Laptop on 24/11/2019.
 * ViewModel para el manejo de las novedades
 */


class NoveltyViewModel(application: Application) : AndroidViewModel(application) {

    private val employeeRepository = EmployeeRepository(application)
    private val noveltyRepository = NoveltyRepository(application)
    val actualPerson = MutableLiveData<Employee>()

    fun saveNovelty(employeeId: Int, month: Int, value: Int, type: String) {

        viewModelScope.launch {
            val defered = async { employeeRepository.getEmployeeById(employeeId = employeeId) }
            val foundEmployee = defered.await()
            val novelty = Novelty(
                employee = foundEmployee,
                type = NoveltyType.values().first { it.valueToShow == type },
                value = value,
                month = month,
                reportDate = Date()
            )
            noveltyRepository.saveNovelty(novelty)
            actualPerson.postValue(employeeRepository.getEmployeeFullByIdAndMonth(employeeId))
        }

    }

}