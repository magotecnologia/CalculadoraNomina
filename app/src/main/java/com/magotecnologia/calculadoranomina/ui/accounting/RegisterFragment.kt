package com.magotecnologia.calculadoranomina.ui.accounting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.magotecnologia.calculadoranomina.R
import com.magotecnologia.calculadoranomina.ui.adapters.AccountingAdapter
import kotlinx.android.synthetic.main.register_fragment.*

class RegisterFragment : Fragment() {

    companion object {
        fun newInstance() = RegisterFragment()
    }

    private lateinit var viewModel: RegisterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.register_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel::class.java)
        details.adapter =
            AccountingAdapter(details = emptyList(), context = context!!.applicationContext)
        details.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        viewModel.billingDetails.observe(viewLifecycleOwner, Observer {
            (details.adapter as AccountingAdapter).details = it
            (details.adapter as AccountingAdapter).notifyDataSetChanged()
        })
        viewModel.getData()
    }

}
