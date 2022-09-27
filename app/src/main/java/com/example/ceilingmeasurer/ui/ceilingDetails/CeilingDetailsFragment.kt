package com.example.ceilingmeasurer.ui.ceilingDetails

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.core.graphics.drawable.toBitmap
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.transition.TransitionInflater
import com.example.ceilingmeasurer.R
import com.example.ceilingmeasurer.databinding.FragmentCeilingDetailsBinding
import com.example.ceilingmeasurer.domain.entities.Ceiling
import com.example.ceilingmeasurer.temp.PlanFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class CeilingDetailsFragment : Fragment() {
    private var _binding: FragmentCeilingDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var ceiling: Ceiling
    private lateinit var takePicture: ActivityResultLauncher<Void?>
    private lateinit var requestPermission: ActivityResultLauncher<String?>

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
        registerForActivities()
    }

    private fun registerForActivities() {
        requestPermission =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted: Boolean ->
                if (isGranted) {
                    takePicture.launch()
                }
            }
        takePicture = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            binding.imagePlan.setImageBitmap(it)
        }
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
        initCeiling()
        initButtons()
        initImageViewClickListener()
    }

    private fun initImageViewClickListener() {
        binding.imagePlan.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.client_list_container,
                    ImageFragment.newInstance(binding.imagePlan.drawable.toBitmap())
                )
                .addToBackStack("")
                .commit()

        }
    }

    private fun initCeiling() {
        binding.apply {
            name.setText(ceiling.name)
            material.setText(ceiling.name_material)
            length.setText(ceiling.length.toString())
            width.setText(ceiling.width.toString())
            twoSteps.setText(ceiling.two_steps.toString())
            corners.setText(ceiling.corners.toString())
            stroke.setText(ceiling.stroke.toString())
            lamps.setText(ceiling.lamps.toString())
            chandeliers.setText(ceiling.chandeliers.toString())
            curtain.setText(ceiling.curtain.toString())
            aluCurtain.setText(ceiling.alu_curtain.toString())
            priceForM2.setText(ceiling.price_for_m2.toString())
        }
    }

    private fun initButtons() {
        buttonOpenPlan()
        buttonAddPhoto()
        buttonSave()
    }

    private fun buttonOpenPlan() {
        binding.buttonPlan.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(
                    R.id.client_list_container,
                    PlanFragment.newInstance(ceiling.length.toString(), ceiling.width.toString())
                )
                .addToBackStack("")
                .commit()
        }
    }

    private fun buttonAddPhoto() {
        binding.buttonAddPhoto.setOnClickListener {
            requestPermission.launch(Manifest.permission.CAMERA)
        }
    }

    private fun buttonSave() {
        binding.buttonSaveCeiling.setOnClickListener {
            viewModel.saveCeiling(getCeiling())
        }
    }

    private fun getCeiling(): Ceiling {
        return Ceiling(
            id = ceiling.id,
            clientId = ceiling.clientId,
            name = binding.name.text.toString(),
            name_material = binding.material.text.toString(),
            length = binding.length.toString().toDoubleOrNull() ?: 0.0,
            width = binding.width.toString().toDoubleOrNull() ?: 0.0,
            chandeliers = binding.chandeliers.toString().toIntOrNull() ?: 0,
            lamps = binding.lamps.toString().toIntOrNull() ?: 0,
            corners = binding.corners.toString().toIntOrNull() ?: 0,
            stroke = binding.stroke.toString().toIntOrNull() ?: 0,
            two_steps = binding.twoSteps.toString().toDoubleOrNull() ?: 0.0,
            curtain = binding.curtain.toString().toDoubleOrNull() ?: 0.0,
            alu_curtain = binding.aluCurtain.toString().toDoubleOrNull() ?: 0.0,
            price_for_m2 = binding.priceForM2.toString().toDoubleOrNull() ?: 0.0,
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}