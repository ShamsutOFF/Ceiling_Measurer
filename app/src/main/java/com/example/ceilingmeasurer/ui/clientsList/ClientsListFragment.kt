package com.example.ceilingmeasurer.ui.clientsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ceilingmeasurer.R
import com.example.ceilingmeasurer.databinding.FragmentClientsListBinding
import com.example.ceilingmeasurer.domain.entities.Client
import com.example.ceilingmeasurer.ui.clientDetails.ClientDetailsFragment
import com.example.ceilingmeasurer.ui.clientsList.recycler.ClientsListAdapter
import com.example.ceilingmeasurer.utils.IOnBackPressed
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClientsListFragment : Fragment(), IOnBackPressed {

    private var _binding: FragmentClientsListBinding? = null
    private val binding get() = _binding!!
    private val adapter = ClientsListAdapter { position ->
        onItemClick(position)
    }
    private val viewModel: ClientsListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClientsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initButton()
        initRecycler()
        initViewModel()
        renderData()
    }

    private fun initRecycler() {
        binding.clientListRecyclerView.layoutManager = GridLayoutManager(context, 1)
        binding.clientListRecyclerView.adapter = adapter
    }

    private fun onItemClick(position: Int) {
        initChildFragment(ClientDetailsFragment.newInstance(adapter.getData()[position]))
    }

    private fun initButton() {
        binding.clientListAddButton.setOnClickListener {
            initChildFragment(ClientDetailsFragment.newInstance(Client()))
        }
    }

    private fun initChildFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.client_list_container, fragment)
            .addToBackStack("")
            .commit()
    }

    private fun initViewModel() {
        viewModel.clientList.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }

    private fun renderData() {
        viewModel.getClientList()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    override fun onBackPressed(): Boolean {
        childFragmentManager.popBackStack()
        return true
    }
}