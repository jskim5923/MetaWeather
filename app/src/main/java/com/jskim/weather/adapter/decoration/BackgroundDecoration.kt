package com.jskim.weather.adapter.decoration

import android.content.Context
import android.graphics.Canvas
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jskim.weather.R
import com.jskim.weather.adapter.WeatherRecyclerAdapter

class BackgroundDecoration(private val context: Context) : RecyclerView.ItemDecoration() {
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        for (i in 0 until parent.childCount) {
            val view = parent.getChildAt(i)
            if (parent.adapter?.getItemViewType(i) == WeatherRecyclerAdapter.ItemType.TITLE.ordinal) {
                view.setBackgroundColor(ContextCompat.getColor(context, R.color.recycler_title_background_color))
            }
        }
    }
}