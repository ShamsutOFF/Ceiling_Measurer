package com.example.ceilingmeasurer.ui.clientDetails.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ceilingmeasurer.databinding.RecyclerItemCeilingBinding
import com.example.ceilingmeasurer.domain.entities.Ceiling

class ClientDetailsViewHolder(
    private val binding: RecyclerItemCeilingBinding,
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
        ): ClientDetailsViewHolder =
            ClientDetailsViewHolder(
                RecyclerItemCeilingBinding.inflate(LayoutInflater.from(parent.context)),
                onItemClick
            )
    }

    fun bind(ceiling: Ceiling) {
        binding.material.setText(ceiling.name_material)
        binding.square.setText(ceiling.square.toString())
        binding.perimeter.setText(ceiling.perimeter.toString())
        binding.chandeliers.setText(ceiling.chandeliers.toString())
        binding.lamps.setText(ceiling.lamps.toString())
        binding.corners.setText(ceiling.corners.toString())
        binding.stroke.setText(ceiling.stroke.toString())
        binding.twoSteps.setText(ceiling.two_steps.toString())
        binding.curtain.setText(ceiling.curtain.toString())
        binding.aluCurtain.setText(ceiling.alu_curtain.toString())
        binding.priceForM2.setText(ceiling.price_for_m2.toString())
        binding.attachment.setText(ceiling.attachment)
        binding.drawing.setText(ceiling.drawing)
    }
}