package com.example.ceilingmeasurer.ui.materialDetails

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.example.ceilingmeasurer.R
import com.example.ceilingmeasurer.databinding.FragmentMaterialDetailBinding
import com.example.ceilingmeasurer.domain.entities.Material
import org.koin.androidx.viewmodel.ext.android.viewModel

class MaterialDetailsFragment : Fragment() {
    private var _binding: FragmentMaterialDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var material: Material

    private val viewModel: MaterialDetailsViewModel by viewModel()

    companion object {
        private const val MATERIAL = "material"
        fun newInstance(material: Material) = MaterialDetailsFragment().apply {
            arguments = bundleOf(MATERIAL to material)
            this.material = material
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMaterialDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initMaterial()
        initSaveButton()
    }

    private fun initMaterial() {
        binding.apply {
            nameMaterial.setText(material.name_material)
            unitMeasure.setText(material.unit_measure)
            priceMaterial.setText(material.unit_price.toString())
            priceWork.setText(material.unit_work_price.toString())
        }
    }

    private fun initSaveButton() {
        binding.buttonMaterialSave.setOnClickListener {
            viewModel.saveMaterial(getMaterial())
            Handler(Looper.getMainLooper()).postDelayed({
                parentFragmentManager.popBackStack()
            }, 500)
        }
    }

    private fun getMaterial() = Material(
        id = material.id,
        name_material = binding.nameMaterial.text.toString(),
        unit_measure = binding.unitMeasure.text.toString(),
        unit_price = binding.priceMaterial.text.toString().toDoubleOrNull() ?: 0.0,
        unit_work_price = binding.priceWork.text.toString().toDoubleOrNull() ?: 0.0
    )

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}