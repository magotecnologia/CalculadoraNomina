package com.magotecnologia.calculadoranomina.ui.company

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.magotecnologia.calculadoranomina.data.repository.EmployeeRepository
import com.magotecnologia.calculadoranomina.domain.CompanyContribution
import com.magotecnologia.calculadoranomina.domain.Employee
import kotlinx.coroutines.launch


class CompanyReductionsViewModel(application: Application) : AndroidViewModel(application) {
    private val employeeRepository = EmployeeRepository(application)
    private val _billingDetails = MutableLiveData<CompanyContribution>()
    val billingDetails: LiveData<CompanyContribution> = _billingDetails


    fun getCompanyData() {
        viewModelScope.launch {
            val employees: List<Employee?> = employeeRepository.getAllEmploteeFull()
            val contributions = employees.filterNotNull().map { it.getCompanyReduction() }
            if (contributions.isNotEmpty()) {
                val contribution = contributions.reduce(
                    operation = { previous, element ->
                        CompanyContribution(
                            health = previous.health.plus(element.health),
                            pension = previous.pension.plus(element.pension),
                            professionalInsurance = previous.professionalInsurance.plus(element.professionalInsurance),
                            familyCompensation = previous.familyCompensation.plus(element.familyCompensation),
                            familyBienestar = previous.familyBienestar.plus(element.familyBienestar),
                            Sena = previous.Sena.plus(element.Sena)
                        )
                    }
                )
                _billingDetails.postValue(contribution)
            }
        }
    }
}






