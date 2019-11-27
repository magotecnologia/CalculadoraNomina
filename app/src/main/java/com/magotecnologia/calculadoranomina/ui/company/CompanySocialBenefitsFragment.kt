package com.magotecnologia.calculadoranomina.ui.company

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.magotecnologia.calculadoranomina.R
import com.magotecnologia.calculadoranomina.ui.toMoneyString
import kotlinx.android.synthetic.main.company_social_benefits_fragment.*

class CompanySocialBenefitsFragment : Fragment() {

    companion object {
        fun newInstance() = CompanySocialBenefitsFragment()
    }

    private lateinit var viewModel: CompanySocialBenefitsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.company_social_benefits_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CompanySocialBenefitsViewModel::class.java)
        viewModel.billingDetails.observe(viewLifecycleOwner, Observer {
            layoff.text = it.layoffs.toMoneyString()
            layoffInterest.text = it.layoffsInterest.toMoneyString()
            socialBonus.text = it.socialBonus.toMoneyString()
            vacations.text = it.vacations.toMoneyString()
        })
        viewModel.getCompanyData()
    }

}
