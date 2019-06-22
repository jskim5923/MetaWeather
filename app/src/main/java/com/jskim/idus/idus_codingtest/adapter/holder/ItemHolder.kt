package com.jskim.idus.idus_codingtest.adapter.holder

import androidx.recyclerview.widget.RecyclerView
import com.jskim.idus.idus_codingtest.databinding.HolderItemBinding
import com.jskim.idus.idus_codingtest.model.Weather

class ItemHolder(private val binding: HolderItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(weather: Weather) {
        binding.weather = weather
        binding.executePendingBindings()
    }


}