package com.example.ceilingmeasurer.ui.clientDetails.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ceilingmeasurer.R
import com.example.ceilingmeasurer.databinding.RecyclerItemCeilingBinding
import com.example.ceilingmeasurer.domain.entities.Ceiling

class ClientDetailsViewHolder(
    private val binding: RecyclerItemCeilingBinding,
    private val onItemClick: (position: Int) -> Unit,
) :
    RecyclerView.ViewHolder(binding.root) {
    init {
        itemView.setOnClickListener {
            onItemClick(adapterPosition)
        }
    }

    var ceilingCardView = binding.ceilingCardView

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClick: (position: Int) -> Unit
        ): ClientDetailsViewHolder =
            ClientDetailsViewHolder(
                RecyclerItemCeilingBinding.inflate(LayoutInflater.from(parent.context)),
                onItemClick
            )
    }

    fun bind(ceiling: Ceiling) {
        binding.apply {
            if (ceiling.name_material.isEmpty()) ceilingName.text =
                itemView.context.getString(R.string.new_ceiling)
            else ceilingName.text = ceiling.name
            if (ceiling.length.equals(0.0) || ceiling.width.equals(0.0)) ceilingSize.text = ""
            else ceilingSize.text = (ceiling.length * ceiling.width).toString() + "m^2"
            ceilingMaterial.text = ceiling.name_material
        }
    }
}