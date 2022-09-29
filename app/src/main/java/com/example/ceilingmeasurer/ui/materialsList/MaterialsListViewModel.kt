package com.example.ceilingmeasurer.ui.materialsList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ceilingmeasurer.domain.MaterialListRepo
import com.example.ceilingmeasurer.domain.entities.Material
import com.example.ceilingmeasurer.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MaterialsListViewModel(private val repo: MaterialListRepo) : BaseViewModel() {
    private val _liveData: MutableLiveData<List<Material>> = MutableLiveData()
    val materialList: LiveData<List<Material>> = _liveData

    fun getMaterialList() {
        cancelJob()
        viewModelCoroutineScope.launch { fetchClientList() }
    }

    private suspend fun fetchClientList() {
        withContext(Dispatchers.IO) {
            _liveData.postValue(repo.getMaterialList())
        }
    }

    fun insertNewMaterial(material: Material) {
        cancelJob()
        viewModelCoroutineScope.launch { susInsertNewMaterial(material) }
    }

    private suspend fun susInsertNewMaterial(material: Material) {
        withContext(Dispatchers.IO) {
            repo.insertMaterial(material)
        }
    }
}