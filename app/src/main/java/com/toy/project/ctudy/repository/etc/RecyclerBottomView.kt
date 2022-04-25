package com.toy.project.ctudy.repository.etc

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerBottomView : RecyclerView.ItemDecoration() {

    private val DEFAULT_PADDING = 50

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State,
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        outRect.bottom = DEFAULT_PADDING;
    }
}