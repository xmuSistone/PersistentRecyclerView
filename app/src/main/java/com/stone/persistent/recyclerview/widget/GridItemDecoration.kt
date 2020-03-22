package com.stone.persistent.recyclerview.widget

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager

/**
 * RecyclerView间距Decoration
 */
class GridItemDecoration(divider: Float) : RecyclerView.ItemDecoration() {

    private val divider = divider.toInt()

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {

        val layoutParams = view.layoutParams as StaggeredGridLayoutManager.LayoutParams
        val spanIndex = layoutParams.spanIndex

        if (spanIndex == 0) {
            outRect.left = divider
            outRect.right = divider / 2
        } else {
            outRect.right = divider
            outRect.left = divider / 2
        }

        outRect.bottom = divider
        outRect.top = if (layoutParams.viewAdapterPosition < 2) {
            divider
        } else {
            0
        }

    }
}