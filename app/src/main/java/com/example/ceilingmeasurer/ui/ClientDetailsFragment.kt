package com.example.ceilingmeasurer.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.ceilingmeasurer.databinding.FragmentClientDetailsBinding
import com.example.ceilingmeasurer.domain.entities.Client

class ClientDetailsFragment : Fragment() {
    private var _binding: FragmentClientDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var client: Client

    companion object {
        private const val CLIENT = "client"
        fun newInstance(client: Client) = ClientDetailsFragment().apply {
            arguments = bundleOf(CLIENT to client)
            this.client = client
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentClientDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.client.text = client.name + " " + client.surname
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}