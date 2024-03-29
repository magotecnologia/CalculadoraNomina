package com.magotecnologia.calculadoranomina.ui.employee


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.magotecnologia.calculadoranomina.R
import com.magotecnologia.calculadoranomina.ui.toMoneyString
import kotlinx.android.synthetic.main.fragment_employee_month_report.*

/**
 * A simple [Fragment] subclass.
 */
class EmployeeMonthReportFragment : Fragment() {

    private lateinit var viewModel: EmployeeMonthReportViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_employee_month_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EmployeeMonthReportViewModel::class.java)
        viewModel.billingDetails.observe(viewLifecycleOwner, Observer {
            earnedSubtotal.text = it.earns.total.toMoneyString()
            basicSalary.text = it.earns.basicSalary.toMoneyString()
            transportationAllowance.text = it.earns.allowance.toMoneyString()
            extraTime.text = it.earns.extraTime.toMoneyString()
            bonus.text = it.earns.bonus.toMoneyString()
            commissions.text = it.earns.commissions.toMoneyString()
            reductionsSubtotal.text = it.deductions.total.toMoneyString()
            healthReduction.text = it.deductions.health.toMoneyString()
            pensionReduction.text = it.deductions.pension.toMoneyString()
            loanReduction.text = it.deductions.loan.toMoneyString()
            foreClosureReduction.text = it.deductions.foreClosure.toMoneyString()
            employeeFundReduction.text = it.deductions.employeeFund.toMoneyString()
            netTotal.text = (it.earns.total - it.deductions.total).toMoneyString()
        })
        setClickListeners()
        arguments?.let {
            val employeeId = EmployeeMonthReportFragmentArgs.fromBundle(it).employeeId
            viewModel.getEmployeeData(employeeId = employeeId)
        }


    }

    private fun setClickListeners() {
        earnedSubtotalContainer.setOnClickListener { earnedDetails.changeVisibility() }
        reductionsContainer.setOnClickListener { reductionsDetails.changeVisibility() }
    }


    fun View.changeVisibility() {
        this.visibility = if (this.visibility == View.VISIBLE) View.GONE else View.VISIBLE
    }
}
