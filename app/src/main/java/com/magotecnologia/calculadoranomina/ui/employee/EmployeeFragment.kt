package com.magotecnologia.calculadoranomina.ui.employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.magotecnologia.calculadoranomina.R
import com.magotecnologia.calculadoranomina.ui.toMoneyString
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EmployeeViewModel::class.java)
        viewModel.employeeData.observe(viewLifecycleOwner, Observer {
            it?.let {
                employeeName.text = it.firstName
                employeeLastName.text = it.lastName
                employeeDni.text = it.Dni.toMoneyString()
                employeePhone.text = it.phoneNumber
                employeePosition.text = it.position
                employeeSalary.text = it.salary.toMoneyString()
            }
        })
        arguments?.let {
            val employeeId = EmployeeFragmentArgs.fromBundle(it).employeeId
            viewModel.getEmployeeData(employeeId)
            fab.setOnClickListener { button ->
                val action = EmployeeFragmentDirections.actionEmployeeFragmentToNoveltyFragment(
                    employeeId,
                    11
                )
                Navigation.findNavController(view).navigate(action)
            }
            bill.setOnClickListener { billButton ->
                val action =
                    EmployeeFragmentDirections.actionEmployeeFragmentToEmployeeMonthReportFragment(
                        employeeId = employeeId, month = 11
                    )
                Navigation.findNavController(view).navigate(action)
            }
        }


    }

}
