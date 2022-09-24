package com.example.ceilingmeasurer.ui.clientsList.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ceilingmeasurer.databinding.RecyclerItemClientBinding
import com.example.ceilingmeasurer.domain.entities.Client


class ClientsListViewHolder(
    private val binding: RecyclerItemClientBinding,
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
        ): ClientsListViewHolder =
            ClientsListViewHolder(
                RecyclerItemClientBinding.inflate(LayoutInflater.from(parent.context)),
                onItemClick
            )
    }

    fun bind(client: Client) {
        binding.apply {
            clientName.text = client.name
            clientSurname.text = client.surname
            clientAddress.text = client.address
            clientStatus.text = client.status
        }
    }
}