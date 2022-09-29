package com.example.ceilingmeasurer.ui.materialDetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.transition.TransitionInflater
import com.example.ceilingmeasurer.R
import com.example.ceilingmeasurer.databinding.FragmentMaterialDetailBinding
import com.example.ceilingmeasurer.domain.entities.Material

class MaterialDetailsFragment : Fragment() {
    private var _binding: FragmentMaterialDetailBinding? = null
    private val binding get() = _binding!!
    private lateinit var material: Material

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMaterialDetailBinding.inflate(inflater, container, false)
        return binding.root
    }
    companion object {
        private const val MATERIAL = "material"
        fun newInstance(material: Material) = MaterialDetailsFragment().apply {
            arguments = bundleOf(MATERIAL to material)
            this.material = material
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}