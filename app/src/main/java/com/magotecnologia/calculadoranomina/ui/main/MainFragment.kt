package com.magotecnologia.calculadoranomina.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.magotecnologia.calculadoranomina.R
import com.magotecnologia.calculadoranomina.ui.adapters.EmployeeAdapter
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        employee_list.adapter = EmployeeAdapter(emptyList(), context = context!!.applicationContext)
        employee_list.layoutManager =
            GridLayoutManager(context!!.applicationContext, 2, LinearLayoutManager.VERTICAL, false)
        viewModel.employeeList.observe(viewLifecycleOwner, Observer {
            (employee_list.adapter as EmployeeAdapter).employeeList = it
            (employee_list.adapter as EmployeeAdapter).notifyDataSetChanged()
        })
        fab.setOnClickListener { button ->
            val action = MainFragmentDirections.actionMainFragmentToNewEmployeeFragment()
            Navigation.findNavController(view).navigate(action)
        }
        viewModel.getAllEmployees()
    }


}
