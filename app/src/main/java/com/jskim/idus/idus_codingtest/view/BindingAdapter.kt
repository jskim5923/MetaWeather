package com.jskim.idus.idus_codingtest.view

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jskim.idus.idus_codingtest.adapter.WeatherRecyclerAdapter
import com.jskim.idus.idus_codingtest.model.Weather
import kotlinx.android.synthetic.main.activity_weather.view.*

object BindingAdapter {
    @JvmStatic
    @BindingAdapter("icon")
    fun ImageView.loadIcon(name: String) {
        context.let {
            val id = it.resources.getIdentifier("ic_$name", "drawable", it.packageName)
            Glide.with(context)
                .load(id)
                .into(this)
        }
    }

    @JvmStatic
    @BindingAdapter("updateItem")
    fun RecyclerView.updateItem(list: List<Weather>?) {
        list?.let {
            (recyclerView.adapter as WeatherRecyclerAdapter).updateItem(list)
        }
    }
}