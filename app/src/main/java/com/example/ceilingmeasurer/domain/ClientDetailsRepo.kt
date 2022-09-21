package com.example.ceilingmeasurer.domain

import com.example.ceilingmeasurer.domain.entities.Ceiling

interface ClientDetailsRepo {
    suspend fun getCeilings(id: Int): List<Ceiling>
    suspend fun saveCeiling(ceiling: Ceiling)
    suspend fun updateCeilings(ceilingList: List<Ceiling>)
    suspend fun deleteCeiling(ceiling: Ceiling)
}