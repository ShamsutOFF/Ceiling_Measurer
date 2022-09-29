package com.example.ceilingmeasurer.ui.materialsList.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ceilingmeasurer.domain.entities.Material
import com.example.ceilingmeasurer.ui.materialsList.recycler.MaterialsListViewHolder

class MaterialsListAdapter(private val onItemClick: (position: Int) -> Unit) :
    RecyclerView.Adapter<MaterialsListViewHolder>() {
    private var data: List<Material> = emptyList()

    fun getData() = data

    fun setData(materialsList: List<Material>) {
        data = materialsList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialsListViewHolder =
        MaterialsListViewHolder.create(parent, onItemClick)

    override fun onBindViewHolder(holder: MaterialsListViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}