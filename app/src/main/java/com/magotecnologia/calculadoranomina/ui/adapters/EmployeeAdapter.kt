package com.magotecnologia.calculadoranomina.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.magotecnologia.calculadoranomina.R
import com.magotecnologia.calculadoranomina.domain.Employee
import com.magotecnologia.calculadoranomina.ui.loadImage
import com.magotecnologia.calculadoranomina.ui.main.MainFragmentDirections
import kotlinx.android.synthetic.main.cardview_employee.view.*

/**
 * Created by Marco-Laptop on 24/11/2019.
 * Adaptador para la muestra de imagenes en la pantalla inicial
 */


class EmployeeAdapter(var employeeList: List<Employee>, private val context: Context) :
    RecyclerView.Adapter<EmployeeAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.cardview_employee, parent, false)
        view.layoutParams.width = parent.width / 2
        view.requestLayout()
        return ViewHolder(view)
    }

    override fun getItemCount() = employeeList.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(employee = employeeList[position])
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val image: ImageView = itemView.cardviewEmployeeImage
        private val employeeName: TextView = itemView.cardviewEmployeeName

        private fun navigate(view: View, employee: Employee) {
            val action =
                MainFragmentDirections.actionMainFragmentToEmployeeFragment(employeeId = employee.id)
            Navigation.findNavController(view).navigate(action)
        }


        fun bind(employee: Employee) {
            if (employee.photoPath == null) image.load(R.drawable.ic_person)
            else image.loadImage(employee.photoPath)
            employeeName.text = "${employee.firstName} ${employee.lastName}"
            image.setOnClickListener { navigate(it, employee = employee) }
            employeeName.setOnClickListener { navigate(it, employee = employee) }
        }
    }

}