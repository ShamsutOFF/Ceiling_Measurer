package com.example.ceilingmeasurer.ui.ceilingDetails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.ceilingmeasurer.domain.ClientDetailsRepo
import com.example.ceilingmeasurer.domain.MaterialListRepo
import com.example.ceilingmeasurer.domain.entities.Ceiling
import com.example.ceilingmeasurer.domain.entities.Material
import com.example.ceilingmeasurer.ui.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CeilingDetailsViewModel(
    private val repoCeilings: ClientDetailsRepo,
    private val repoMaterials: MaterialListRepo
) : BaseViewModel() {

    private val _liveMaterialsList: MutableLiveData<List<Material>> = MutableLiveData()
    val materialsList: LiveData<List<Material>> = _liveMaterialsList

    fun saveCeiling(ceiling: Ceiling) {
        cancelJob()
        viewModelCoroutineScope.launch { susUpdateCeilings(ceiling) }
    }

    private suspend fun susUpdateCeilings(ceiling: Ceiling) {
        repoCeilings.updateCeiling(ceiling)
    }

    fun getMaterialsList() {
        cancelJob()
        viewModelCoroutineScope.launch { susGetMaterialList() }
    }

    private suspend fun susGetMaterialList() {
        repoMaterials.getMaterialList()
        withContext(Dispatchers.IO) {
            _liveMaterialsList.postValue(repoMaterials.getMaterialList())
        }
    }
}
