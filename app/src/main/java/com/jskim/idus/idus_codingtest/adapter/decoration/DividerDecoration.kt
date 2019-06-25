package com.jskim.idus.idus_codingtest.adapter.decoration

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jskim.idus.idus_codingtest.R
import com.jskim.idus.idus_codingtest.adapter.WeatherRecyclerAdapter.*

class DividerDecoration(private val context: Context) : RecyclerView.ItemDecoration() {
    private var mDivider: Paint

    init {
        mDivider = Paint().apply {
            color = ContextCompat.getColor(context, R.color.recycler_title_divider_color)
            strokeWidth = dpToPx(1F)
        }

    }

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = (view.layoutParams as RecyclerView.LayoutParams).viewAdapterPosition

        if (position < 0) {
            return
        }

        outRect.set(
            mDivider.strokeWidth.toInt(),
            if (position == ItemType.TITLE.ordinal) {
                mDivider.strokeWidth.toInt()
            } else {
                0
            },
            mDivider.strokeWidth.toInt(),
            0
        )
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        val rowCount = parent.childCount
        for (i in 0 until rowCount) {
            val rowViewGroup = parent.getChildAt(i) as ViewGroup
            if (parent.adapter?.getItemViewType(i) == ItemType.TITLE.ordinal) {
                //top of header
                c.drawLine(
                    rowViewGroup.left.toFloat(),
                    rowViewGroup.top.toFloat(),
                    rowViewGroup.right.toFloat(),
                    rowViewGroup.top.toFloat(),
                    mDivider
                )
            }

            //bottom
            c.drawLine(
                rowViewGroup.left.toFloat(),
                rowViewGroup.bottom.toFloat(),
                rowViewGroup.right.toFloat(),
                rowViewGroup.bottom.toFloat(),
                mDivider
            )


            val cellCount = rowViewGroup.childCount
            for (j in 0 until rowViewGroup.childCount) {
                val cell = rowViewGroup.getChildAt(j)
                //left
                c.drawLine(
                    cell.left.toFloat() + mDivider.strokeWidth,
                    rowViewGroup.top.toFloat(),
                    cell.left.toFloat() + mDivider.strokeWidth,
                    rowViewGroup.bottom.toFloat(),
                    mDivider
                )

                if (j == cellCount - 1) {
                    //right of last cell
                    c.drawLine(
                        rowViewGroup.right - mDivider.strokeWidth,
                        rowViewGroup.top.toFloat(),
                        rowViewGroup.right - mDivider.strokeWidth,
                        rowViewGroup.bottom.toFloat(),
                        mDivider
                    )
                }
            }
        }
    }

    private fun dpToPx(dp: Float) = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp, context.resources.displayMetrics
    )
}