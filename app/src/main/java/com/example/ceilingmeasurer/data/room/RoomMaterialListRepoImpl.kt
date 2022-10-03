package com.example.ceilingmeasurer.data.room

import com.example.ceilingmeasurer.data.mapper.MaterialMapper
import com.example.ceilingmeasurer.data.room.dao.MaterialsDAO
import com.example.ceilingmeasurer.domain.MaterialListRepo
import com.example.ceilingmeasurer.domain.entities.Material

class RoomMaterialListRepoImpl(private val dao: MaterialsDAO) : MaterialListRepo {
    override suspend fun getMaterialList(): List<Material> {
        val tempList = dao.getAllMaterials()
        val returnList = mutableListOf<Material>()
        for (materialEntity in tempList) {
            returnList.add(MaterialMapper().fromModel(materialEntity))
        }
        return returnList
    }

    override suspend fun updateMaterial(material: Material) {
        dao.updateMaterial(MaterialMapper().toModel(material))
    }

    override suspend fun insertMaterial(material: Material) {
        dao.insertNewMaterial(MaterialMapper().toModel(material))
    }

    override suspend fun deleteMaterial(material: Material) {
        dao.deleteMaterial(MaterialMapper().toModel(material))
    }
}