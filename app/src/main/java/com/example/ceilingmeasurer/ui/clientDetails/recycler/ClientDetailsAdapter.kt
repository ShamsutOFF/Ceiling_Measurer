package com.example.ceilingmeasurer.ui.clientDetails.recycler

import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.example.ceilingmeasurer.domain.entities.Ceiling

class ClientDetailsAdapter(private val onItemClick: (position: Int) -> Unit) :
    RecyclerView.Adapter<ClientDetailsViewHolder>() {
    private var data: List<Ceiling> = mutableListOf()

    fun setData(clientsList: List<Ceiling>) {
        data = clientsList
        notifyDataSetChanged()
    }

    fun addNewCeiling() {
        val newData = mutableListOf<Ceiling>()
        for (element in data) {
            newData.add(element)
        }
        newData.add(Ceiling())
        data = newData
        notifyItemInserted(data.size)
    }

    fun getData(): List<Ceiling> = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientDetailsViewHolder =
        ClientDetailsViewHolder.create(parent, onItemClick)

    override fun onBindViewHolder(holder: ClientDetailsViewHolder, position: Int) {
        holder.bind(data[position])
        bindTextWatchers(holder, position)
    }

    private fun bindTextWatchers(holder: ClientDetailsViewHolder, position: Int) {
        holder.name.doAfterTextChanged {
            data[position].name = it.toString()
        }
        holder.material.doAfterTextChanged {
            data[position].name_material = it.toString()
        }
        holder.length.doAfterTextChanged {
            data[position].length = it.toString().toDoubleOrNull() ?: 0.0
        }
        holder.width.doAfterTextChanged {
            data[position].width = it.toString().toDoubleOrNull() ?: 0.0
        }
        holder.chandeliers.doAfterTextChanged {
            data[position].chandeliers = it.toString().toIntOrNull() ?: 0
        }
        holder.lamps.doAfterTextChanged {
            data[position].lamps = it.toString().toIntOrNull() ?: 0
        }
        holder.corners.doAfterTextChanged {
            data[position].corners = it.toString().toIntOrNull() ?: 0
        }
        holder.stroke.doAfterTextChanged {
            data[position].stroke = it.toString().toIntOrNull() ?: 0
        }
        holder.twoSteps.doAfterTextChanged {
            data[position].two_steps = it.toString().toDoubleOrNull() ?: 0.0
        }
        holder.curtain.doAfterTextChanged {
            data[position].curtain = it.toString().toDoubleOrNull() ?: 0.0
        }
        holder.aluCurtain.doAfterTextChanged {
            data[position].alu_curtain = it.toString().toDoubleOrNull() ?: 0.0
        }
        holder.priceForM2.doAfterTextChanged {
            data[position].price_for_m2 = it.toString().toDoubleOrNull() ?: 0.0
        }
        holder.attachment.doAfterTextChanged {
            data[position].attachment = it.toString()
        }
    }

    override fun getItemCount(): Int = data.size
}
