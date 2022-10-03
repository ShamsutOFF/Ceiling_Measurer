package com.example.ceilingmeasurer.domain

import com.example.ceilingmeasurer.domain.entities.Material

interface MaterialListRepo {
    suspend fun getMaterialList(): List<Material>
    suspend fun insertMaterial(material: Material)
    suspend fun updateMaterial(material: Material)
    suspend fun deleteMaterial(material: Material)
}