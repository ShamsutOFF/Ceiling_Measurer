package com.example.ceilingmeasurer.ui.clientsList

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.ceilingmeasurer.domain.entities.Client

class ClientsListAdapter : RecyclerView.Adapter<ClientsListViewHolder>() {
    private var data: List<Client> = emptyList()

    fun setData(clientsList: List<Client>) {
        data = clientsList
    }

    fun getData() = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientsListViewHolder =
        ClientsListViewHolder.create(parent)

    override fun onBindViewHolder(holder: ClientsListViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}