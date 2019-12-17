package com.magotecnologia.calculadoranomina.ui.novelty


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.magotecnologia.calculadoranomina.R
import com.magotecnologia.calculadoranomina.domain.NoveltyType
import kotlinx.android.synthetic.main.fragment_novelty.*

/**
 * A simple [Fragment] subclass.
 */
class NoveltyFragment : Fragment() {
    private lateinit var viewModel: NoveltyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_novelty, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(NoveltyViewModel::class.java)
        noveltyType.adapter = ArrayAdapter<String>(this.context,
            android.R.layout.simple_spinner_dropdown_item,
            NoveltyType.values().map { it.valueToShow })
        viewModel.successFullMessage.observe(viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(this.context, it, Toast.LENGTH_SHORT).show()
            }
        })
        var employeeId = 0
        var month = 0
        arguments?.let {
            employeeId = NoveltyFragmentArgs.fromBundle(it).employeeId
            month = NoveltyFragmentArgs.fromBundle(it).month
        }
        newNoveltyConfirmButton.setOnClickListener {
            if (checkFields()) {
                viewModel.saveNovelty(
                    employeeId = employeeId,
                    month = month,
                    value = noveltyValueEdit.text.toString().toInt(),
                    type = noveltyType.selectedItem.toString()
                )

            } else {
                Toast.makeText(this.context, "FALTAN DATOS", Toast.LENGTH_SHORT).show()
            }
        }
        noveltyType.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedString = noveltyType.getItemAtPosition(position)
                noveltyValueText.text =
                    NoveltyType.values().first { it.valueToShow == selectedString }.measureUnit
            }

        }

    }

    private fun checkFields(): Boolean {
        if (noveltyValueEdit.text.isNullOrBlank()) return false
        return true
    }


}
