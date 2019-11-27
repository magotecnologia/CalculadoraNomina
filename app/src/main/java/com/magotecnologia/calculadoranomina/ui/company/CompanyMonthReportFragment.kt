package com.magotecnologia.calculadoranomina.ui.company

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.magotecnologia.calculadoranomina.R
import com.magotecnologia.calculadoranomina.ui.changeVisibility
import com.magotecnologia.calculadoranomina.ui.toMoneyString
import kotlinx.android.synthetic.main.fragment_employee_month_report.*

class CompanyMonthReportFragment : Fragment() {

    companion object {
        fun newInstance() = CompanyMonthReportFragment()
    }

    private lateinit var viewModel: CompanyMonthReportViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_employee_month_report, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CompanyMonthReportViewModel::class.java)
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
        viewModel.getCompanyData()

    }

    private fun setClickListeners() {
        earnedSubtotalContainer.setOnClickListener { earnedDetails.changeVisibility() }
        reductionsContainer.setOnClickListener { reductionsDetails.changeVisibility() }
    }


}
