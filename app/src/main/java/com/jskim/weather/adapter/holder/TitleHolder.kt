package com.jskim.weather.adapter.holder

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

class TitleHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind() {
        binding.executePendingBindings()
    }

}