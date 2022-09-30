package com.example.ceilingmeasurer.ui.materialDetails

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.example.ceilingmeasurer.R
import com.example.ceilingmeasurer.databinding.FragmentMaterialDetailBinding
import com.example.ceilingmeasurer.domain.entities.Material
import com.example.ceilingmeasurer.ui.materialsList.MaterialsListFragment
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
        initSpinner()
        initSaveButton()
    }

    private fun initMaterial() {
        binding.apply {
            nameMaterial.setText(material.name_material)
            priceMaterial.setText(material.unit_price.toString())
            priceWork.setText(material.unit_work_price.toString())
        }
    }

    private fun initSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.unit_measure_spin,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.unitMeasureSp.adapter = adapter
        }
    }

    private fun initSaveButton() {
        binding.buttonMaterialSave.setOnClickListener {
            viewModel.saveMaterial(getMaterial())
            Handler(Looper.getMainLooper()).postDelayed({
                (parentFragment as MaterialsListFragment).onBackPressed()
            }, 500)
        }
    }

    private fun getMaterial() = Material(
        id = material.id,
        name_material = binding.nameMaterial.text.toString().trim(),
        unit_measure = binding.unitMeasureSp.selectedItem.toString().trim(),
        unit_price = binding.priceMaterial.text.toString().trim().toDoubleOrNull() ?: 0.0,
        unit_work_price = binding.priceWork.text.toString().trim().toDoubleOrNull() ?: 0.0
    )

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}