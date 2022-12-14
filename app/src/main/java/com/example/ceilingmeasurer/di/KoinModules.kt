package com.example.ceilingmeasurer.di

import androidx.room.Room
import com.example.ceilingmeasurer.data.room.RoomClientDetailsRepoImpl
import com.example.ceilingmeasurer.data.room.RoomClientListRepoImpl
import com.example.ceilingmeasurer.data.room.RoomDb
import com.example.ceilingmeasurer.data.room.RoomMaterialListRepoImpl
import com.example.ceilingmeasurer.data.room.dao.CeilingDAO
import com.example.ceilingmeasurer.data.room.dao.ClientDAO
import com.example.ceilingmeasurer.data.room.dao.MaterialsDAO
import com.example.ceilingmeasurer.data.room.utils.RoomConst
import com.example.ceilingmeasurer.domain.ClientDetailsRepo
import com.example.ceilingmeasurer.domain.ClientListRepo
import com.example.ceilingmeasurer.domain.MaterialListRepo
import com.example.ceilingmeasurer.ui.ceilingDetails.CeilingDetailsViewModel
import com.example.ceilingmeasurer.ui.clientDetails.ClientDetailsViewModel
import com.example.ceilingmeasurer.ui.clientsList.ClientsListViewModel
import com.example.ceilingmeasurer.ui.materialDetails.MaterialDetailsViewModel
import com.example.ceilingmeasurer.ui.materialsList.MaterialsListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object KoinModules {

    val repository = module {
        single<RoomDb> {
            Room.databaseBuilder(
                androidApplication(),
                RoomDb::class.java,
                RoomConst.DB_NAME
            ).build()
        }
        single<ClientDAO> {
            get<RoomDb>().ClientDAO()
        }
        single<CeilingDAO> {
            get<RoomDb>().CeilingDAO()
        }
        single<MaterialsDAO> {
            get<RoomDb>().MaterialDAO()
        }

        single<ClientListRepo> { RoomClientListRepoImpl(get<ClientDAO>()) }
        single<ClientDetailsRepo> { RoomClientDetailsRepoImpl(get<CeilingDAO>()) }
        single<MaterialListRepo> { RoomMaterialListRepoImpl(get<MaterialsDAO>()) }
    }

    val viewModel = module {
        viewModel { ClientsListViewModel(get<ClientListRepo>(), get<ClientDetailsRepo>()) }
        viewModel { ClientDetailsViewModel(get<ClientDetailsRepo>(), get<ClientListRepo>()) }
        viewModel { CeilingDetailsViewModel(get<ClientDetailsRepo>(), get<MaterialListRepo>()) }
        viewModel { MaterialsListViewModel(get<MaterialListRepo>()) }
        viewModel { MaterialDetailsViewModel(get<MaterialListRepo>()) }
    }
}