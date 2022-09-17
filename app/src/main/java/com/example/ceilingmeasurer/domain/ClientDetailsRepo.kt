package com.example.ceilingmeasurer.domain

import com.example.ceilingmeasurer.domain.entities.Ceiling

interface ClientDetailsRepo {
    suspend fun getCeilings(name: String, surname: String): List<Ceiling>
    suspend fun saveCeilings(ceiling: List<Ceiling>)
}