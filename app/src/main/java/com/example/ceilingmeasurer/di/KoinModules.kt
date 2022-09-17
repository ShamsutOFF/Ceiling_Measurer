package com.example.ceilingmeasurer.di

import com.example.ceilingmeasurer.data.fake.FakeClientDetailsRepoImpl
import com.example.ceilingmeasurer.data.fake.FakeClientListRepoImpl
import com.example.ceilingmeasurer.domain.ClientDetailsRepo
import com.example.ceilingmeasurer.domain.ClientListRepo
import com.example.ceilingmeasurer.ui.clientDetails.ClientDetailsViewModel
import com.example.ceilingmeasurer.ui.clientsList.ClientsListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object KoinModules {

    val repository = module {
        single<ClientListRepo> { FakeClientListRepoImpl() }
        single<ClientDetailsRepo> { FakeClientDetailsRepoImpl() }
    }

    val viewModel = module {
        viewModel { ClientsListViewModel(get<ClientListRepo>()) }
        viewModel { ClientDetailsViewModel(get<ClientDetailsRepo>(), get<ClientListRepo>()) }
    }
}