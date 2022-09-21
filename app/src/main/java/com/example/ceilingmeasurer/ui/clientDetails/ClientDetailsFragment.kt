package com.example.ceilingmeasurer.ui.clientDetails

import android.os.Bundle
import android.os.Handler
import android.os.Looper
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
        updateData()
    }

    private fun initAddCeilingButton() {
        binding.addCeilingButton.setOnClickListener {
            viewModel.insertNewCeiling(client.id)
            Handler(Looper.getMainLooper()).postDelayed({
                updateData()
            }, 500)
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
            adapter.notifyDataSetChanged()
        }
    }

    private fun initSaveButton() {
        binding.saveButton.setOnClickListener {
            viewModel.updateClientCredentials(getClient())
            viewModel.updateCeilingsDetails(adapter.getData())
        }
    }

    private fun updateData() {
        viewModel.getCeilings(client)
    }

    private fun onItemClick(position: Int) {
        //nothing
    }

    private fun onItemDelete(position: Int) {
        viewModel.deleteCeiling(adapter.getData()[position])
        updateData()
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
//        viewModel.updateCeilingsDetails(adapter.getData())
        super.onDestroy()
        _binding = null
    }

    private fun getClient(): Client {
        val returnClient = arguments?.getParcelable<Client>(CLIENT) ?: Client()
        returnClient.apply {
            name = binding.clientName.text.toString()
            surname = binding.clientSurname.text.toString()
            phone_number = binding.phoneNumber.text.toString()
            address = binding.address.text.toString()
            district = binding.district.text.toString()
            status = binding.clientStatus.text.toString()
        }
        return returnClient
    }
}