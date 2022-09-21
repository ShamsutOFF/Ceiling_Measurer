package com.example.ceilingmeasurer.data.fake

import com.example.ceilingmeasurer.domain.ClientDetailsRepo
import com.example.ceilingmeasurer.domain.entities.Ceiling

class FakeClientDetailsRepoImpl : ClientDetailsRepo {

    override suspend fun getCeilings(id: Int): List<Ceiling> {
        return listOf(Ceiling(0,0))
    }

    override suspend fun saveCeilings(ceiling: List<Ceiling>) {
        //nothing
    }

    override suspend fun deleteCeiling(ceiling: Ceiling) {
        //nothing
    }
}