package com.example.ceilingmeasurer.ui.materialDetails.recycler

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ceilingmeasurer.domain.entities.Material

class MaterialsListAdapter(private val onItemClick: (position: Int) -> Unit):
RecyclerView.Adapter<MaterialsListViewHolder>(){
    private var data: List<Material> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialsListViewHolder =
        MaterialsListViewHolder.create(parent, onItemClick)


    override fun onBindViewHolder(holder: MaterialsListViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun setData(materialsList: List<Material>) {
        data = materialsList
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.size
}