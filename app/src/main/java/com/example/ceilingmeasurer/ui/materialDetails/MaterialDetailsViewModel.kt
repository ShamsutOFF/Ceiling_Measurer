package com.example.ceilingmeasurer.ui.materialDetails

import com.example.ceilingmeasurer.domain.MaterialListRepo
import com.example.ceilingmeasurer.domain.entities.Material
import com.example.ceilingmeasurer.ui.BaseViewModel
import kotlinx.coroutines.launch

class MaterialDetailsViewModel(private val repoMaterials: MaterialListRepo) : BaseViewModel() {

    fun saveMaterial(material: Material) {
        cancelJob()
        viewModelCoroutineScope.launch { susSaveMaterial(material) }
    }

    private suspend fun susSaveMaterial(material: Material) {
        repoMaterials.updateMaterial(material)
    }
}