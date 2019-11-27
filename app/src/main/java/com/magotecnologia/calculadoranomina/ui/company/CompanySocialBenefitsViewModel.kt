package com.magotecnologia.calculadoranomina.ui.company

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.magotecnologia.calculadoranomina.data.repository.EmployeeRepository
import com.magotecnologia.calculadoranomina.domain.Employee
import com.magotecnologia.calculadoranomina.domain.SocialBenefits
import kotlinx.coroutines.launch

class CompanySocialBenefitsViewModel(application: Application) : AndroidViewModel(application) {
    private val employeeRepository = EmployeeRepository(application)
    private val _billingDetails = MutableLiveData<SocialBenefits>()
    val billingDetails: LiveData<SocialBenefits> = _billingDetails

    fun getCompanyData() {
        viewModelScope.launch {
            val employees: List<Employee?> = employeeRepository.getAllEmploteeFull()
            val socialBenefitsPre = employees.filterNotNull().map { it.getSocialBenefits() }
            val socialBenefits = employees.filterNotNull().map { it.getSocialBenefits() }.reduce(
                operation = { previous, element ->
                    SocialBenefits(
                        layoffs = previous.layoffs.plus(element.layoffs),
                        layoffsInterest = previous.layoffsInterest.plus(element.layoffsInterest),
                        socialBonus = previous.socialBonus.plus(element.socialBonus),
                        vacations = previous.vacations.plus(element.vacations)
                    )
                }
            )
            _billingDetails.postValue(socialBenefits)
        }
    }

}
