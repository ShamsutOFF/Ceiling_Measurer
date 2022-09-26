package com.example.ceilingmeasurer.ui.ceilingDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.example.ceilingmeasurer.R
import com.example.ceilingmeasurer.databinding.FragmentCeilingDetailsBinding
import com.example.ceilingmeasurer.domain.entities.Ceiling
import org.koin.androidx.viewmodel.ext.android.viewModel

class CeilingDetailsFragment : Fragment() {
    private var _binding: FragmentCeilingDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var ceiling: Ceiling

    private val viewModel: CeilingDetailsViewModel by viewModel()

    companion object {
        private const val CEILING = "ceiling"
        fun newInstance(ceiling: Ceiling) = CeilingDetailsFragment().apply {
            arguments = bundleOf(CEILING to ceiling)
            this.ceiling = ceiling
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_right)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCeilingDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}