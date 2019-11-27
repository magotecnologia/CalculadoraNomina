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
import kotlinx.android.synthetic.main.company_reductions_fragment.*

class CompanyReductionsFragment : Fragment() {

    companion object {
        fun newInstance() = CompanyReductionsFragment()
    }

    private lateinit var viewModel: CompanyReductionsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.company_reductions_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CompanyReductionsViewModel::class.java)
        viewModel.billingDetails.observe(viewLifecycleOwner, Observer {
            health.text = it.health.toMoneyString()
            pension.text = it.pension.toMoneyString()
            professionalInsurance.text = it.professionalInsurance.toMoneyString()
            familyCompensation.text = it.familyCompensation.toMoneyString()
            family_bienestar.text = it.familyBienestar.toMoneyString()
            sena.text = it.Sena.toMoneyString()
        })
        viewModel.getCompanyData()
    }

}
