package com.jskim.weather.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import com.jskim.weather.databinding.HolderItemBinding
import com.jskim.weather.model.Weather

class ItemHolder(private val binding: HolderItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(weather: Weather) {
        binding.weather = weather
        binding.executePendingBindings()
    }


}