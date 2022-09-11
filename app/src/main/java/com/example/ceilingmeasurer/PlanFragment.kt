package com.example.ceilingmeasurer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.ceilingmeasurer.databinding.FragmentPlanBinding


private const val HEIGHT_PARAM = "HEIGHT_PARAM"
private const val WIDTH_PARAM = "WIDTH_PARAM"

class PlanFragment : Fragment() {
    private var height: String? = null
    private var width: String? = null

    private var _binding: FragmentPlanBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            height = it.getString(HEIGHT_PARAM)
            width = it.getString(WIDTH_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlanBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val r = RectangleTest(activity, height, width)
        binding.containerForDraw.addView(RectangleTest(activity,height,width))
        binding.heightTv.text = height
        binding.widthTv.text = width
    }

    companion object {
        @JvmStatic
        fun newInstance(height: String, width: String) =
            PlanFragment().apply {
                arguments = Bundle().apply {
                    putString(HEIGHT_PARAM, height)
                    putString(WIDTH_PARAM, width)
                }
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}