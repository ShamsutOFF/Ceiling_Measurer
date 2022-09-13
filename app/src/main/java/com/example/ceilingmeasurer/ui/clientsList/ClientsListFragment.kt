package com.example.ceilingmeasurer.ui.clientsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ceilingmeasurer.databinding.FragmentClientsListBinding
import com.example.ceilingmeasurer.domain.entities.Client

class ClientsListFragment : Fragment() {

    private var _binding: FragmentClientsListBinding? = null
    private val binding get() = _binding!!
    private val adapter = ClientsListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClientsListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val fakeList = listOf(
            Client(1, "Andrei", "Roman", "1234", "address1", "9"),
            Client(2, "Adel", "Shamsutoff", "1235", "address2", "7"),
            Client(3, "Vladimir", "", "1236", "address3", "5"),
        )
        binding.clientListRecyclerView.layoutManager = GridLayoutManager(context, 1)
        binding.clientListRecyclerView.adapter = adapter
        adapter.setData(fakeList)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}