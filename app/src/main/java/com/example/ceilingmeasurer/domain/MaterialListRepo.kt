package com.example.ceilingmeasurer.domain

import com.example.ceilingmeasurer.domain.entities.Material

interface MaterialListRepo {
    suspend fun getMaterialList(): List<Material>
    suspend fun saveMaterial(material: Material)
}