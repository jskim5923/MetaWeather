package com.jskim.idus.idus_codingtest.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movie_search.base.BaseRecyclerViewAdapter
import com.jskim.idus.idus_codingtest.adapter.holder.ItemHolder
import com.jskim.idus.idus_codingtest.adapter.holder.TitleHolder
import com.jskim.idus.idus_codingtest.databinding.HolderItemBinding
import com.jskim.idus.idus_codingtest.databinding.HolderTitleBinding
import com.jskim.idus.idus_codingtest.model.Weather

class WeatherRecyclerAdapter : BaseRecyclerViewAdapter<Weather, RecyclerView.ViewHolder>() {
    enum class ItemType {
        TITLE, ITEM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ItemType.TITLE.ordinal -> {
                TitleHolder(HolderTitleBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
            else -> {
                ItemHolder(HolderItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList?.let {
            it.size + 1
        } ?: 0
    }

    override fun getItemViewType(position: Int): Int {
        return when (position) {
            0 -> {
                ItemType.TITLE.ordinal
            }
            else -> {
                ItemType.ITEM.ordinal
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TitleHolder -> {
            }

            is ItemHolder -> {
                dataList?.let {
                    holder.bind(it[position - 1])
                }
            }
        }
    }
}