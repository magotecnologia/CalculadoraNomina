package com.magotecnologia.calculadoranomina.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.magotecnologia.calculadoranomina.R
import com.magotecnologia.calculadoranomina.domain.AccountingDetail
import com.magotecnologia.calculadoranomina.ui.changeVisibility
import com.magotecnologia.calculadoranomina.ui.toMoneyString
import kotlinx.android.synthetic.main.cardview_account_details.view.*

/**
 * Created by Marco-Laptop on 26/11/2019.
 * Adaptador para la muestra de los detalles de contabilidad
 */


class AccountingAdapter(var details: List<AccountingDetail>, private val context: Context) :
    RecyclerView.Adapter<AccountingAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.cardview_account_details, parent, false)
        return ViewHolder(view)
    }


    override fun getItemCount(): Int = details.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(detail = details[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val code: TextView = itemView.code
        private val detailText: TextView = itemView.details
        private val noCredit: TextView = itemView.noCredit
        private val isCredit: TextView = itemView.isCredit
        private val subDetails: RecyclerView = itemView.innerRecycler

        fun bind(detail: AccountingDetail) {
            //TODO:Apariencia
            // code.setTextAppearance(R.style.)
            //if(detail.code==0) code.visibility=View.INVISIBLE
            code.text = detail.code.toString()
            detailText.text = detail.detail
            if (detail.isCredit == false || detail.isCredit == null) noCredit.text =
                detail.value.toMoneyString()
            if (detail.isCredit == true || detail.isCredit == null) isCredit.text =
                detail.value.toMoneyString()
            subDetails.adapter = AccountingAdapter(details = detail.subDetails, context = context)
            subDetails.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            code.setOnClickListener { subDetails.changeVisibility() }
            detailText.setOnClickListener { subDetails.changeVisibility() }
            noCredit.setOnClickListener { subDetails.changeVisibility() }
            isCredit.setOnClickListener { subDetails.changeVisibility() }
        }
    }

}