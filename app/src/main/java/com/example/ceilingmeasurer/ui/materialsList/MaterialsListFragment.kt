package com.example.ceilingmeasurer.ui.materialsList

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.transition.TransitionInflater
import com.example.ceilingmeasurer.R
import com.example.ceilingmeasurer.databinding.FragmentMaterialsListBinding
import com.example.ceilingmeasurer.domain.entities.Material
import com.example.ceilingmeasurer.ui.materialDetails.MaterialDetailsFragment
import com.example.ceilingmeasurer.ui.materialsList.recycler.MaterialsListAdapter
import com.example.ceilingmeasurer.utils.IOnBackPressed
import com.example.ceilingmeasurer.utils.attachLeftSwipeHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

class MaterialsListFragment : Fragment(), IOnBackPressed {
    private var _binding: FragmentMaterialsListBinding? = null
    private val binding get() = _binding!!
    private val materialsAdapter = MaterialsListAdapter { position ->
        onItemClick(position)
    }
    private val viewModel: MaterialsListViewModel by viewModel()

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
        initViewModel()
        initAddNewMaterialButton()
        renderData()
    }

    private fun initAddNewMaterialButton() {
        binding.buttonAddNewMaterial.setOnClickListener {
            viewModel.insertNewMaterial(Material())
            Handler(Looper.getMainLooper()).postDelayed({
                viewModel.getMaterialList()
            }, 1000)
        }
    }

    private fun onItemClick(position: Int) {
        initChildFragment(MaterialDetailsFragment.newInstance(materialsAdapter.getData()[position]))
    }

    private fun initChildFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction()
            .replace(R.id.material_list_container, fragment)
            .addToBackStack("")
            .commit()
    }

    private fun initRecycler() {
        binding.materialsRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 1)
            binding.materialsRecyclerView.adapter = materialsAdapter
        }.attachLeftSwipeHelper { viewHolder ->
            viewModel.deleteMaterial(materialsAdapter.getData()[viewHolder.adapterPosition])
            renderData()
        }
    }

    private fun renderData() {
        viewModel.getMaterialList()
    }

    private fun initViewModel() {
        viewModel.materialList.observe(viewLifecycleOwner) {
            materialsAdapter.setData(it)
        }
    }

    override fun onBackPressed(): Boolean {
        childFragmentManager.popBackStack()
        renderData()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}