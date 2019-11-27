package com.magotecnologia.calculadoranomina.ui.employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.magotecnologia.calculadoranomina.R
import com.magotecnologia.calculadoranomina.domain.Employee
import com.magotecnologia.calculadoranomina.ui.toMoneyString
import kotlinx.android.synthetic.main.fragment_new_employee.*

class NewEmployeeFragment : Fragment() {

    companion object {
        fun newInstance() = NewEmployeeFragment()
    }

    private lateinit var viewModel: NewEmployeeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_employee, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NewEmployeeViewModel::class.java)
        viewModel.successFullMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show()
            }
        })
        newEmployeeConfirmButton.setOnClickListener {
            if (checkFields()) {
                val employeeToCreate = Employee(
                    firstName = employeeName.text.toString(),
                    lastName = employeeLastName.text.toString(),
                    Dni = employeeDni.text.toString().toInt(),
                    position = employeePosition.text.toString(),
                    salary = employeeSalary.text.toString().toInt(),
                    phoneNumber = employeePhone.text.toString()
                )
                viewModel.saveNewEmployee(employeeToCreate)
            } else {
                Toast.makeText(this.context, "FALTAN DATOS", Toast.LENGTH_SHORT).show()
            }
        }
        //employeeSalary.doOnTextChanged { text, start, count, after -> textToMoney(text, start, count, after) }
    }


    fun textToMoney(
        text: CharSequence?,
        start: Int,
        count: Int,
        after: Int
    ) {
        text?.let {
            if (text.isNotBlank()) {
                employeeSalary.removeTextChangedListener(employeeSalary.doOnTextChanged { text, start, count, after ->
                    textToMoney(
                        text,
                        start,
                        count,
                        after
                    )
                })
                employeeSalary.setText(text.toString().replace("$", "").toInt().toMoneyString())
                employeeSalary.doOnTextChanged { text, start, count, after ->
                    textToMoney(text, start, count, after)
                }
                employeeSalary.setSelection(text.length + 1)
            }
        }
    }


    private fun checkFields(): Boolean {
        if (employeeName.text.isNullOrBlank()) return false
        if (employeeLastName.text.isNullOrBlank()) return false
        if (employeeDni.text.isNullOrBlank()) return false
        if (employeePosition.text.isNullOrBlank()) return false
        if (employeePosition.text.isNullOrBlank()) return false
        if (employeeSalary.text.isNullOrBlank()) return false
        return true
    }


}
