package com.example.ceilingmeasurer.ui.clientDetails.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.ceilingmeasurer.domain.entities.Ceiling

class CeilingsCallback(private val oldList: List<Ceiling>, private val newList: List<Ceiling>) :
    DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size
    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (oldList[oldItemPosition].id == newList[newItemPosition].id)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val (_, value, name) = oldList[oldItemPosition]
        val (_, value1, name1) = newList[newItemPosition]
        return name == name1 && value == value1
    }
}