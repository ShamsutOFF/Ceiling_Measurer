package com.example.ceilingmeasurer.data.fake

import com.example.ceilingmeasurer.domain.ClientDetailsRepo
import com.example.ceilingmeasurer.domain.entities.Ceiling

class FakeClientDetailsRepoImpl : ClientDetailsRepo {

    override suspend fun getCeilings(id: Int): List<Ceiling> {
        return listOf(Ceiling())
    }

    override suspend fun saveCeiling(ceiling: Ceiling) {
        //nothing
    }

    override suspend fun updateCeilings(ceilingList: List<Ceiling>) {
        //nothing
    }

    override suspend fun deleteCeiling(ceiling: Ceiling) {
        //nothing
    }

    override suspend fun deleteCeilingByClientId(id: Int) {
        //nothing
    }
}