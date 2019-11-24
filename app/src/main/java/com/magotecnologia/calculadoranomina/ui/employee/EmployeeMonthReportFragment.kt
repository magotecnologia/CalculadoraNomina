package com.magotecnologia.calculadoranomina.ui.employee


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.magotecnologia.calculadoranomina.R

/**
 * A simple [Fragment] subclass.
 */
class EmployeeMonthReportFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_employee_month_report, container, false)
    }


}
