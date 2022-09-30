package com.example.ceilingmeasurer.data.fake


import com.example.ceilingmeasurer.domain.MaterialListRepo
import com.example.ceilingmeasurer.domain.entities.Material

class FakeMaterialListRepoImpl : MaterialListRepo {

    val material = mutableListOf(
        Material(1, "Глянцевый белый потолок", "м2", 320.0, 360.0),
        Material(2, "Матовый белый потолок", "м2", 240.0, 320.0),
        Material(3, "Глянцевый жёлтый потолок", "м2", 650.0, 400.0),
    )

    override suspend fun getMaterialList(): List<Material> {
        return material
    }

    override suspend fun insertMaterial(material: Material) {
        this.material.add(material)
    }

    override suspend fun updateMaterial(material: Material) {
        //nothing
    }

    override suspend fun deleteMaterial(material: Material) {
        //nothing
    }
}