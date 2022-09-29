package com.example.ceilingmeasurer.ui.ceilingDetails

import com.example.ceilingmeasurer.domain.ClientDetailsRepo
import com.example.ceilingmeasurer.domain.entities.Ceiling
import com.example.ceilingmeasurer.ui.BaseViewModel
import kotlinx.coroutines.launch

class CeilingDetailsViewModel(private val repoCeilings: ClientDetailsRepo) : BaseViewModel() {

    fun saveCeiling(ceiling: Ceiling) {
        cancelJob()
        viewModelCoroutineScope.launch { susUpdateCeilings(ceiling) }
    }

    private suspend fun susUpdateCeilings(ceiling: Ceiling) {
        repoCeilings.updateCeiling(ceiling)
    }
}
