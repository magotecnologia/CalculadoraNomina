package com.magotecnologia.calculadoranomina.ui.employee

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.magotecnologia.calculadoranomina.R

class newEmployeeFragment : Fragment() {

    companion object {
        fun newInstance() = newEmployeeFragment()
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
        // TODO: Use the ViewModel
    }

}
