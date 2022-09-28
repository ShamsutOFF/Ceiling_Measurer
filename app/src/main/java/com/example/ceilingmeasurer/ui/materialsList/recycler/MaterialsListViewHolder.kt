package com.example.ceilingmeasurer.ui.materialsList.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ceilingmeasurer.databinding.RecyclerItemMaterialBinding
import com.example.ceilingmeasurer.domain.entities.Material

class MaterialsListViewHolder(
    private val binding: RecyclerItemMaterialBinding,
    private val onItemClick: (position: Int) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    init {
        itemView.setOnClickListener {
            onItemClick(adapterPosition)
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClick: (position: Int) -> Unit
        ): MaterialsListViewHolder =
            MaterialsListViewHolder(
                RecyclerItemMaterialBinding.inflate(LayoutInflater.from(parent.context)),
                onItemClick
            )
    }

    fun bind(material: Material) {
        binding.apply {
            rvMatName.text = material.name_material
            rvMatUnitMeasure.text = material.unit_measure
            rvMatPriceMaterial.text = material.unit_price.toString()
            rvMatPriceWork.text = material.unit_work_price.toString()
        }
    }
}

