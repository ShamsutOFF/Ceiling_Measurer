package com.example.ceilingmeasurer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.ceilingmeasurer.R
import com.example.ceilingmeasurer.databinding.FragmentMaterialsBinding

class MaterialsFragment :Fragment(){

    private var _binding:FragmentMaterialsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMaterialsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
    }

    private fun initRecycler() {
        binding.materialsRecyclerView.layoutManager = GridLayoutManager(context, 1)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}