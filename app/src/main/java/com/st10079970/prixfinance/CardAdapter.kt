package com.st10079970.prixfinance

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class CardAdapter(private val budgetList: MutableList<CardItems>) : RecyclerView.Adapter<CardAdapter.ListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.cardview, parent, false)
        return ListViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ListViewHolder,position: Int) {
        val currentItem = budgetList[position]
        holder.setName.text = currentItem.name
        holder.setBalance.text = currentItem.balance.toString()
        holder.setStatus.text = currentItem.status
    }

    //counts the number of floats or cards to display
    override fun getItemCount(): Int = budgetList.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val setName: TextView = itemView.findViewById(R.id.cardName)
        val setBalance: TextView = itemView.findViewById(R.id.cardBalance)
        val setStatus: TextView = itemView.findViewById(R.id.cardStatus)
    }
}