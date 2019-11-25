package com.magotecnologia.calculadoranomina.ui.employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.magotecnologia.calculadoranomina.R
import kotlinx.android.synthetic.main.employee_fragment.*

class EmployeeFragment : Fragment() {

    companion object {
        fun newInstance() = EmployeeFragment()
    }

    private lateinit var viewModel: EmployeeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.employee_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EmployeeViewModel::class.java)
        viewModel.employeeData.observe(viewLifecycleOwner, Observer {
            it?.let {
                employeeName.text = it.firstName
                employeeLastName.text = it.lastName
                employeeDni.text = it.Dni.toString()
                employeePhone.text = it.phoneNumber
                employeePosition.text = it.position
                employeeSalary.text = it.salary.toString()
            }
        })
        arguments?.let {
            val employeeId = EmployeeFragmentArgs.fromBundle(it).employeeId
            viewModel.getEmployeeData(employeeId)
        }

    }

}
