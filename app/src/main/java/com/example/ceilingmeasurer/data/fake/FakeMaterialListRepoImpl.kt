package com.example.ceilingmeasurer.data.fake


import com.example.ceilingmeasurer.domain.MaterialListRepo
import com.example.ceilingmeasurer.domain.entities.Material

class FakeMaterialListRepoImpl: MaterialListRepo {

    val material = mutableListOf(
        Material("Глянцевый белый потолок", "м2", 320, 360),
        Material("Матовый белый потолок", "м2", 240, 320),
        Material("Глянцевый жёлтый потолок", "м2", 650, 400),
    )

    override suspend fun getMaterialList(): List<Material> {
        return material
    }

    override suspend fun saveMaterial(material: Material) {
        this.material.add(material)
    }
}