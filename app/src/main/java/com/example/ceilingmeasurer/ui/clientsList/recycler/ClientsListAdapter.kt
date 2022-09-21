package com.example.ceilingmeasurer.ui.clientsList.recycler

import android.os.CountDownTimer
import android.util.Log
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.example.ceilingmeasurer.R
import com.example.ceilingmeasurer.domain.entities.Client

class ClientsListAdapter(
    private val onItemClick: (position: Int) -> Unit,
) :
    RecyclerView.Adapter<ClientsListViewHolder>() {
    private var data: List<Client> = emptyList()

    fun setData(clientsList: List<Client>) {
        data = clientsList
        notifyDataSetChanged()
    }

    fun getData() = data

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientsListViewHolder =
        ClientsListViewHolder.create(parent, onItemClick)

    override fun onBindViewHolder(holder: ClientsListViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int = data.size
}