package com.example.ceilingmeasurer.ui.clientDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.transition.TransitionInflater
import com.example.ceilingmeasurer.R
import com.example.ceilingmeasurer.databinding.FragmentClientDetailsBinding
import com.example.ceilingmeasurer.domain.entities.Client
import com.example.ceilingmeasurer.temp.PlanFragment
import com.example.ceilingmeasurer.ui.clientDetails.recycler.ClientDetailsAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class ClientDetailsFragment : Fragment() {
    private var _binding: FragmentClientDetailsBinding? = null
    private val binding get() = _binding!!
    private lateinit var client: Client
    private val adapter = ClientDetailsAdapter(
        { position -> onItemClick(position) },
        { position -> onOpenPlan(position) },
        { position -> onAddPhoto(position) },
        { position -> onItemDelete(position) })

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
        initRecycler()
        initClient()
        initViewModel()
        initSaveButton()
        initAddCeilingButton()
        renderData()
    }

    private fun initAddCeilingButton() {
        binding.addCeilingButton.setOnClickListener {
            adapter.addNewCeiling()
            binding.ceilingsRecyclerView.scrollToPosition(adapter.itemCount - 1)
        }
    }

    private fun initClient() {
        binding.apply {
            clientName.setText(client.name)
            clientSurname.setText(client.surname)
            phoneNumber.setText(client.phone_number)
            address.setText(client.address)
            district.setText(client.district)
            clientStatus.setText(client.status)
        }
    }

    private fun initRecycler() {
        binding.ceilingsRecyclerView.layoutManager = GridLayoutManager(context, 1)
        binding.ceilingsRecyclerView.adapter = adapter
    }

    private fun initViewModel() {
        viewModel.ceilingList.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }

    private fun initSaveButton() {
        binding.saveButton.setOnClickListener {
            viewModel.updateClientCredentials(getClient())
            viewModel.updateCeilingsDetails(adapter.getData())
            parentFragmentManager.popBackStack()
        }
    }

    private fun renderData() {
        viewModel.getCeilings(client)
    }

    private fun onItemClick(position: Int) {
        //nothing
    }

    private fun onItemDelete(position: Int) {
        adapter.deleteCeiling(position)
    }

    private fun onAddPhoto(position: Int) {
        //nothing
    }

    private fun onOpenPlan(position: Int) {
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.client_list_container,
                PlanFragment.newInstance(
                    adapter.getData()[position].length.toString(),
                    adapter.getData()[position].width.toString()
                )
            )
            .addToBackStack("")
            .commit()
    }


    override fun onDestroy() {
//        viewModel.updateClientCredentials(getClient())
//        viewModel.updateCeilingsDetails(getCeilings())
        super.onDestroy()
        _binding = null
    }

    private fun getClient(): Client = Client(
        -1,
        binding.clientName.text.toString(),
        binding.clientSurname.text.toString(),
        binding.phoneNumber.text.toString(),
        binding.address.text.toString(),
        binding.district.text.toString(),
        binding.clientStatus.text.toString()
    )
}