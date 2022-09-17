package com.example.ceilingmeasurer.data.fake

import com.example.ceilingmeasurer.domain.ClientDetailsRepo
import com.example.ceilingmeasurer.domain.entities.Ceiling

class FakeClientDetailsRepoImpl: ClientDetailsRepo {
    override suspend fun getCeilings(name: String, surname: String): List<Ceiling> {
        return listOf(Ceiling())
    }

    override suspend fun saveCeilings(ceiling: List<Ceiling>) {
        //nothing
    }
}