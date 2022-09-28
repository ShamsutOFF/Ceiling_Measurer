package com.example.ceilingmeasurer.ui.ceilingDetails

import com.example.ceilingmeasurer.domain.ClientDetailsRepo
import com.example.ceilingmeasurer.domain.entities.Ceiling
import com.example.ceilingmeasurer.ui.BaseViewModel
import kotlinx.coroutines.launch

class CeilingDetailsViewModel(
    private val repoCeilings: ClientDetailsRepo
) : BaseViewModel() {

    fun saveCeiling(ceiling: Ceiling) {
        cancelJob()
        viewModelCoroutineScope.launch { susGetCeilings(ceiling) }
    }

    private suspend fun susGetCeilings(ceiling: Ceiling) {
        repoCeilings.updateCeiling(ceiling)
    }
}
