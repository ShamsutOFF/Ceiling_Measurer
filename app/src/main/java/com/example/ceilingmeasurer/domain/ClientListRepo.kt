package com.example.ceilingmeasurer.domain

import com.example.ceilingmeasurer.domain.entities.Client

interface ClientListRepo {
    fun getClientList(): List<Client>
}
