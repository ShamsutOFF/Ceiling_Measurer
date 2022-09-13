package com.example.ceilingmeasurer.domain.entities

data class Client (
    val id: Long,
    val name: String,
    val surname: String,
    val phone_number: String,
    val address: String,
    val district: String
)