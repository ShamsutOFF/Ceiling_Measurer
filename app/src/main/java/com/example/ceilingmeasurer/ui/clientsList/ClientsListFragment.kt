package com.example.ceilingmeasurer.ui.clientsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ceilingmeasurer.data.fake.FakeClientListRepoImpl
import com.example.ceilingmeasurer.databinding.FragmentClientsListBinding

class ClientsListFragment : Fragment() {

    private var _binding: FragmentClientsListBinding? = null
    private val binding get() = _binding!!
    private val adapter = ClientsListAdapter()
    private val viewModel: ClientsListInterface = ClientsListViewModel(FakeClientListRepoImpl())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClientsListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.clientListRecyclerView.layoutManager = GridLayoutManager(context, 1)
        binding.clientListRecyclerView.adapter = adapter
        adapter.setData(viewModel.getClientList())
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}