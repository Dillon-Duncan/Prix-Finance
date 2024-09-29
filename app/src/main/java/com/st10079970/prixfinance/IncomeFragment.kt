package com.st10079970.prixfinance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import android.widget.EditText

class IncomeFragment : Fragment() {

    // List to store income values
    private val incomeList = mutableListOf<Double>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_income, container, false)

        // Reference to the Button
        val btnIncome = view.findViewById<Button>(R.id.btnIncome)

        // Set OnClickListener for the Button
        btnIncome.setOnClickListener {
            showIncomeDialog()
        }

        return view
    }

    private fun showIncomeDialog() {
        // Create an EditText to input income
        val input = EditText(requireContext())
        input.hint = "Enter amount in Rands"
        input.inputType = android.text.InputType.TYPE_CLASS_NUMBER or android.text.InputType.TYPE_NUMBER_FLAG_DECIMAL

        // Create the AlertDialog
        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Add Income")
            .setMessage("Enter the amount of income in Rands")
            .setView(input)
            .setPositiveButton("Add") { dialog, _ ->
                val incomeText = input.text.toString()
                if (incomeText.isNotEmpty()) {
                    try {
                        val income = incomeText.toDouble()
                        addIncome(income)
                    } catch (e: NumberFormatException) {
                        Toast.makeText(requireContext(), "Invalid amount", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "Please enter a value", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }

    // Private method to add income to the list
    private fun addIncome(income: Double) {
        incomeList.add(income)
        Toast.makeText(requireContext(), "Income added successfully", Toast.LENGTH_SHORT).show()
    }

    private fun getIncomeList(): List<Double> {
        return incomeList
    }


}
