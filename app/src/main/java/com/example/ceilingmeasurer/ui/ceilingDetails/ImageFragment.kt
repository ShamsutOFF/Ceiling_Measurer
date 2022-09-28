package com.example.ceilingmeasurer.ui.ceilingDetails

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.ceilingmeasurer.databinding.FragmentFullImageBinding

class ImageFragment : Fragment() {
    private var _binding: FragmentFullImageBinding? = null
    private val binding get() = _binding!!
    private lateinit var picture: Bitmap

    companion object {
        private const val BITMAP = "bitmap"
        fun newInstance(bitmap: Bitmap) = ImageFragment().apply {
            arguments = bundleOf(BITMAP to bitmap)
            this.picture = bitmap
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFullImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.imageFullScreen.setImageBitmap(picture)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
