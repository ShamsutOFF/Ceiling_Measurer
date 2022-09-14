package com.example.ceilingmeasurer.ui.clientsList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ceilingmeasurer.R
import com.example.ceilingmeasurer.data.fake.FakeClientListRepoImpl
import com.example.ceilingmeasurer.databinding.FragmentClientsListBinding
import com.example.ceilingmeasurer.domain.entities.Client
import com.example.ceilingmeasurer.ui.ClientDetailsFragment
import com.example.ceilingmeasurer.utils.IOnBackPressed

class ClientsListFragment : Fragment(), IOnBackPressed {

    private var _binding: FragmentClientsListBinding? = null
    private val binding get() = _binding!!
    private val adapter = ClientsListAdapter()
    private val viewModel = ClientsListViewModel(FakeClientListRepoImpl())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClientsListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        iniFab()
        initViewModel()
        renderData()
    }

    private fun initRecycler() {
        binding.clientListRecyclerView.layoutManager = GridLayoutManager(context, 1)
        binding.clientListRecyclerView.adapter = adapter
    }

    private fun iniFab() {
        binding.clientListFab.setOnClickListener {
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