package com.example.ceilingmeasurer.ui.clientsList

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ceilingmeasurer.databinding.RecyclerItemClientBinding
import com.example.ceilingmeasurer.domain.entities.Client

class ClientsListViewHolder(private val binding: RecyclerItemClientBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {
        fun create(parent: ViewGroup): ClientsListViewHolder =
            ClientsListViewHolder(RecyclerItemClientBinding.inflate(LayoutInflater.from(parent.context)))
    }

    fun bind(item: Client) {
        binding.clientName.text = item.name
        binding.clientSurname.text = item.surname
        binding.clientAddress.text = item.address
        binding.clientStatus.text = item.status
    }
}