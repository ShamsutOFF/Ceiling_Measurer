package com.example.ceilingmeasurer.data.room

import com.example.ceilingmeasurer.data.mapper.CeilingMapper
import com.example.ceilingmeasurer.data.room.dao.CeilingDAO
import com.example.ceilingmeasurer.domain.ClientDetailsRepo
import com.example.ceilingmeasurer.domain.entities.Ceiling

class RoomClientDetailsRepoImpl(private val dao: CeilingDAO) : ClientDetailsRepo {
    override suspend fun getCeilings(id: Int): List<Ceiling> {
        val tempList = dao.getAllCeilingsByClientId(id)
        val returnList = mutableListOf<Ceiling>()
        for (ceilingEntity in tempList) {
            returnList.add(CeilingMapper().fromModel(ceilingEntity))
        }
        return returnList
    }

    override suspend fun saveCeiling(ceiling: Ceiling) {
        dao.insertNewCeiling(CeilingMapper().toModel(ceiling))
    }

    override suspend fun deleteCeiling(ceiling: Ceiling) {
        dao.deleteCeiling(CeilingMapper().toModel(ceiling))
    }

    override suspend fun deleteCeilingByClientId(id: Int) {
        dao.deleteCeilingByClientId(id)
    }

    override suspend fun updateCeiling(ceiling: Ceiling) {
        dao.updateCeiling(CeilingMapper().toModel(ceiling))
    }
}