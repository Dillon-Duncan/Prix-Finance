package com.st10079970.prixfinance

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import android.widget.EditText
import java.text.SimpleDateFormat
import java.util.*

class ExpensesFragment : Fragment() {

    // List to store expenses as a data class
    private val expensesList = mutableListOf<Expense>()
    private var totalIncome: Double = 10000.0 // Example income, replace with actual value

    // To display the list of expenses
    private lateinit var tvExpenses: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_expenses, container, false)

        // Reference to the TextView and Button
        val btnExpenses = view.findViewById<Button>(R.id.btnExpenses)
        tvExpenses = view.findViewById(R.id.tvExpenses)

        btnExpenses.setOnClickListener {
            showExpenseDialog()
        }

        return view
    }

    private fun showExpenseDialog() {
        // Inflate a custom view for the dialog
        val dialogView = layoutInflater.inflate(R.layout.dialog_add_expense, null)
        val etExpenseName = dialogView.findViewById<EditText>(R.id.etExpenseName)
        val etExpenseAmount = dialogView.findViewById<EditText>(R.id.etExpenseAmount)
        val etExpenseLocation = dialogView.findViewById<EditText>(R.id.etExpenseLocation)
        val etExpenseDate = dialogView.findViewById<EditText>(R.id.etExpenseDate)

        etExpenseDate.setOnClickListener {
            showDatePicker(etExpenseDate)
        }

        val dialog = AlertDialog.Builder(requireContext())
            .setTitle("Add Expense")
            .setView(dialogView)
            .setPositiveButton("Add") { dialog, _ ->
                val expenseName = etExpenseName.text.toString()
                val expenseAmountText = etExpenseAmount.text.toString()
                val expenseLocation = etExpenseLocation.text.toString()
                val expenseDate = etExpenseDate.text.toString()

                if (expenseName.isNotEmpty() && expenseAmountText.isNotEmpty() && expenseLocation.isNotEmpty() && expenseDate.isNotEmpty()) {
                    try {
                        val expenseAmount = expenseAmountText.toDouble()
                        if (expenseAmount <= totalIncome) {
                            addExpense(expenseName, expenseLocation, expenseDate, expenseAmount)
                        } else {
                            Toast.makeText(requireContext(), "Insufficient income", Toast.LENGTH_SHORT).show()
                        }
                    } catch (e: NumberFormatException) {
                        Toast.makeText(requireContext(), "Invalid amount", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(requireContext(), "All fields are required", Toast.LENGTH_SHORT).show()
                }
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()

        dialog.show()
    }

    private fun addExpense(name: String, location: String, date: String, amount: Double) {
        totalIncome -= amount

        // Create a new expense object
        val expense = Expense(name, location, date, amount, totalIncome)
        expensesList.add(expense)

        updateExpensesDisplay()
    }

    private fun updateExpensesDisplay() {
        val expensesText = expensesList.joinToString(separator = "\n\n") { expense ->
            """
            ${expense.name}                     R ${expense.amount}
            ${expense.location}                 ${expense.date}
                                                R ${expense.totalIncomeAfterExpense}
            """.trimIndent()
        }
        tvExpenses.text = "Transactions:\n$expensesText"
    }

    // Private method to show the DatePickerDialog
    private fun showDatePicker(etExpenseDate: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, selectedDay ->
            // Format the selected date and set it to the EditText
            val formattedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(
                GregorianCalendar(selectedYear, selectedMonth, selectedDay).time
            )
            etExpenseDate.setText(formattedDate)
        }, year, month, day)

        datePickerDialog.show()
    }

    data class Expense(
        val name: String,
        val location: String,
        val date: String,
        val amount: Double,
        val totalIncomeAfterExpense: Double
    )
}