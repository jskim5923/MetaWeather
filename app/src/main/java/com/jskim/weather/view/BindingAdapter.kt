package com.jskim.weather.view

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jskim.weather.adapter.WeatherRecyclerAdapter
import com.jskim.weather.model.Weather
import kotlinx.android.synthetic.main.activity_weather.view.*

object BindingAdapter {
    @JvmStatic
        @BindingAdapter(value = ["icon", "placeHolder"], requireAll = false)
    fun ImageView.loadIcon(name: String, placeHolder:Drawable?) {
        val imageId = context.resources.getIdentifier(
            "ic_$name",
            "drawable",
            context.packageName
        )

        Glide.with(context)
            .load(imageId)
            .placeholder(placeHolder)
            .centerCrop()
            .into(this)
    }

    @JvmStatic
    @BindingAdapter("updateItem")
    fun RecyclerView.updateItem(list: List<Weather>?) {
        list?.let {
            (recyclerView.adapter as WeatherRecyclerAdapter).updateItem(list)
        }
    }
}