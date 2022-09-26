package com.example.ceilingmeasurer.domain

import com.example.ceilingmeasurer.domain.entities.Ceiling

interface ClientDetailsRepo {
    suspend fun getCeilings(id: Int): List<Ceiling>
    suspend fun saveCeiling(ceiling: Ceiling)
    suspend fun updateCeiling(ceiling: Ceiling)
    suspend fun deleteCeiling(ceiling: Ceiling)
    suspend fun deleteCeilingByClientId(id: Int)
}