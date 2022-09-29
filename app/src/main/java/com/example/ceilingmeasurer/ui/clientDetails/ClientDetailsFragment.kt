package com.example.ceilingmeasurer.ui.clientDetails

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.transition.TransitionInflater
import com.example.ceilingmeasurer.R
import com.example.ceilingmeasurer.databinding.FragmentClientDetailsBinding
import com.example.ceilingmeasurer.domain.entities.Client
import com.example.ceilingmeasurer.ui.ceilingDetails.CeilingDetailsFragment
import com.example.ceilingmeasurer.ui.clientDetails.recycler.CeilingsCallback
import com.example.ceilingmeasurer.ui.clientDetails.recycler.ClientDetailsAdapter
import com.example.ceilingmeasurer.utils.IOnBackPressed
import com.example.ceilingmeasurer.utils.ImageSaver
import com.example.ceilingmeasurer.utils.attachLeftSwipeHelper
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClientDetailsFragment : Fragment(), IOnBackPressed {
    private var _binding: FragmentClientDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var client: Client
    private val ceilingsAdapter = ClientDetailsAdapter { position -> onItemClick(position) }

    private val viewModel: ClientDetailsViewModel by viewModel()

    companion object {
        private const val CLIENT = "client"
        fun newInstance(client: Client) = ClientDetailsFragment().apply {
            arguments = bundleOf(CLIENT to client)
            this.client = client
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
        _binding = FragmentClientDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClient()
        initSpinner()
        initRecycler()
        initViewModel()
        initSaveButton()
        initAddCeilingButton()
        updateData()
    }

    private fun initSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.status_client,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.clientStatus.adapter = adapter
        }
    }

    private fun initAddCeilingButton() {
        binding.addCeilingButton.setOnClickListener {
            viewModel.insertNewCeiling(client.id)
            Handler(Looper.getMainLooper()).postDelayed({
                updateData()
            }, 1000)
        }
    }

    private fun initClient() {
        binding.apply {
            clientName.setText(client.name)
            clientSurname.setText(client.surname)
            phoneNumber.setText(client.phone_number)
            address.setText(client.address)
            district.setText(client.district)
        }
    }

    private fun initRecycler() {
        binding.ceilingsRecyclerView.apply {
            layoutManager = GridLayoutManager(context, 1)
            adapter = ceilingsAdapter
        }.attachLeftSwipeHelper { viewHolder ->
            ImageSaver(requireContext())
                .setFileName("image${client.id}&${ceilingsAdapter.getData()[viewHolder.adapterPosition].id}")
                .deleteFile()
            viewModel.deleteCeiling(ceilingsAdapter.getData()[viewHolder.adapterPosition])
            updateData()
        }
    }

    private fun initViewModel() {
        viewModel.ceilingList.observe(viewLifecycleOwner) { newData ->
            val diffCallback = CeilingsCallback(ceilingsAdapter.getData(), newData)
            val diffCeilings = DiffUtil.calculateDiff(diffCallback)
            ceilingsAdapter.setData(newData)
            diffCeilings.dispatchUpdatesTo(ceilingsAdapter)
        }
    }

    private fun initSaveButton() {
        binding.saveButton.setOnClickListener {
            viewModel.updateClientCredentials(getClient())
            Handler(Looper.getMainLooper()).postDelayed({
                onBackPressed()
            }, 500)
        }
    }

    private fun updateData() {
        viewModel.getCeilings(client)
    }

    private fun onItemClick(position: Int) {
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.client_list_container,
                CeilingDetailsFragment.newInstance(
                    ceiling = ceilingsAdapter.getData()[position]
                )
            )
            .addToBackStack("")
            .commit()
    }

    override fun onDestroy() {
//        viewModel.updateClientCredentials(getClient())
//        viewModel.updateCeilingsDetails(adapter.getData())
        super.onDestroy()
        _binding = null
    }

    override fun onBackPressed(): Boolean {
        parentFragmentManager.popBackStack()
        updateData()
        return true
    }

    private fun getClient(): Client {
        val returnClient = arguments?.getParcelable<Client>(CLIENT) ?: Client()
        returnClient.apply {
            name = binding.clientName.text.toString()
            surname = binding.clientSurname.text.toString()
            phone_number = binding.phoneNumber.text.toString()
            address = binding.address.text.toString()
            district = binding.district.text.toString()
            status = binding.clientStatus.selectedItem.toString()
        }
        return returnClient
    }
}