package com.magotecnologia.calculadoranomina.ui.employee

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.magotecnologia.calculadoranomina.data.repository.EmployeeRepository

class NewEmployeeViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = EmployeeRepository(application)
}
