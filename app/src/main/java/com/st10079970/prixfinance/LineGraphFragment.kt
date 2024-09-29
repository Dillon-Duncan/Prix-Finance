package com.st10079970.prixfinance

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class LineGraphFragment : Fragment() {

    private lateinit var lineGraphView: LineGraphView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_line_graph, container, false)
        lineGraphView = view.findViewById(R.id.lineGraphView)

        val incomeData = arguments?.getIntegerArrayList("incomeData")?.toList() ?: emptyList()
        val expenseData = arguments?.getIntegerArrayList("expenseData")?.toList() ?: emptyList()

        lineGraphView.incomeData = incomeData
        lineGraphView.expenseData = expenseData
        lineGraphView.invalidate() // Redraw with new data

        return view
    }

    companion object {
        fun newInstance(incomeData: ArrayList<Int>, expenseData: ArrayList<Int>): LineGraphFragment {
            val fragment = LineGraphFragment()
            val args = Bundle()
            args.putIntegerArrayList("incomeData", incomeData)
            args.putIntegerArrayList("expenseData", expenseData)
            fragment.arguments = args
            return fragment
        }
    }
}