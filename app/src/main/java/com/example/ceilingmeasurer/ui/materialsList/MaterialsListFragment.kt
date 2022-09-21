package com.example.ceilingmeasurer.ui.materialsList

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.transition.TransitionInflater
import com.example.ceilingmeasurer.R
import com.example.ceilingmeasurer.databinding.FragmentMaterialsListBinding
import com.example.ceilingmeasurer.ui.MaterialDetailsFragment
import com.example.ceilingmeasurer.ui.materialDetails.recycler.MaterialsListAdapter
import com.example.ceilingmeasurer.utils.IOnBackPressed
import org.koin.androidx.viewmodel.ext.android.viewModel

class MaterialsListFragment : Fragment(), IOnBackPressed {
    private var _binding: FragmentMaterialsListBinding? = null
    private val binding get() = _binding!!
    private val adapter = MaterialsListAdapter { position ->
        onItemClick(position)
    }
    private val viewModel: MaterialsListViewModel by viewModel()

    private fun onItemClick(position: Int) {
            initChildFragment(MaterialDetailsFragment.newInstance(adapter.getData()[position]))
        }

    private fun initChildFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.material_list_container, fragment)
            .addToBackStack("")
            .commit()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        exitTransition = inflater.inflateTransition(R.transition.fade)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMaterialsListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecycler()
        renderData()
        initViewModel()
    }


    private fun initRecycler() {
        binding.materialsRecyclerView.layoutManager = LinearLayoutManager(context)
        binding.materialsRecyclerView.adapter = adapter
    }

    private fun renderData() {
        viewModel.getMaterialList()
    }

    private fun initViewModel() {
        viewModel.materialList.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
}
    override fun onBackPressed(): Boolean {
        childFragmentManager.popBackStack()
        return true
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}