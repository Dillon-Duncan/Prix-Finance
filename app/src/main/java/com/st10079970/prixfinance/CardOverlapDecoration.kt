package com.st10079970.prixfinance

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class CardOverlapDecoration(private val overlapValue: Int = -60) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.set(0, 0, 0, overlapValue)
    }
}