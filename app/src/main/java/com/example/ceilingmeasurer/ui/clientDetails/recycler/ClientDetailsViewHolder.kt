package com.example.ceilingmeasurer.ui.clientDetails.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ceilingmeasurer.databinding.RecyclerItemCeilingBinding
import com.example.ceilingmeasurer.domain.entities.Ceiling

class ClientDetailsViewHolder(
    private val binding: RecyclerItemCeilingBinding,
    private val onItemClick: (position: Int) -> Unit,
    private val onOpenPlan: (position: Int) -> Unit,
    private val onAddPhoto: (position: Int) -> Unit,
    private val onItemDelete: (position: Int) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {
    init {
        itemView.setOnClickListener {
            onItemClick(adapterPosition)
        }
    }

    val name = binding.name
    val material = binding.material
    val length = binding.length
    val width = binding.width
    val chandeliers = binding.chandeliers
    val lamps = binding.lamps
    val corners = binding.corners
    val stroke = binding.stroke
    val twoSteps = binding.twoSteps
    val curtain = binding.curtain
    val aluCurtain = binding.aluCurtain
    val priceForM2 = binding.priceForM2
    val attachment = binding.attachment

    companion object {
        fun create(
            parent: ViewGroup,
            onItemClick: (position: Int) -> Unit,
            onOpenPlan: (position: Int) -> Unit,
            onAddPhoto: (position: Int) -> Unit,
            onItemDelete: (position: Int) -> Unit
        ): ClientDetailsViewHolder =
            ClientDetailsViewHolder(
                RecyclerItemCeilingBinding.inflate(LayoutInflater.from(parent.context)),
                onItemClick,
                onOpenPlan,
                onAddPhoto,
                onItemDelete
            )
    }

    fun bind(ceiling: Ceiling) {
        binding.apply {
            name.setText(ceiling.name)
            material.setText(ceiling.name_material)
            length.setText(ceiling.length.toString())
            width.setText(ceiling.width.toString())
            chandeliers.setText(ceiling.chandeliers.toString())
            lamps.setText(ceiling.lamps.toString())
            corners.setText(ceiling.corners.toString())
            stroke.setText(ceiling.stroke.toString())
            twoSteps.setText(ceiling.two_steps.toString())
            curtain.setText(ceiling.curtain.toString())
            aluCurtain.setText(ceiling.alu_curtain.toString())
            priceForM2.setText(ceiling.price_for_m2.toString())
            attachment.setText(ceiling.attachment)
        }

        binding.buttonDeleteCeiling.setOnClickListener {
            onItemDelete(adapterPosition)
        }

        binding.buttonPlan.setOnClickListener {
            onOpenPlan(adapterPosition)
        }

        binding.buttonAddPhoto.setOnClickListener {
            onAddPhoto(adapterPosition)
        }
    }
}