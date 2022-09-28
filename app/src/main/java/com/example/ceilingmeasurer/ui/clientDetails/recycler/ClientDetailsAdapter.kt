package com.example.ceilingmeasurer.ui.clientDetails.recycler

import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.ceilingmeasurer.R
import com.example.ceilingmeasurer.domain.entities.Ceiling

class ClientDetailsAdapter(
    private val onItemClick: (position: Int) -> Unit
) :
    RecyclerView.Adapter<ClientDetailsViewHolder>() {
    private var data: List<Ceiling> = emptyList()

    fun setData(clientsList: List<Ceiling>) {
        data = clientsList
    }

    fun getData() = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientDetailsViewHolder =
        ClientDetailsViewHolder.create(parent, onItemClick)

    override fun onBindViewHolder(holder: ClientDetailsViewHolder, position: Int) {
        holder.bind(data[position])
        holder.ceilingCardView.animation =
            AnimationUtils.loadAnimation(holder.itemView.context, R.anim.recycler_add)
    }

    override fun getItemCount(): Int = data.size
}
