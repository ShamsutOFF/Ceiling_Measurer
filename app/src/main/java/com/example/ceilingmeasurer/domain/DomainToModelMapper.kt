package com.example.ceilingmeasurer.domain

interface DomainToModelMapper<Domain : Any, Model : Any> {
    fun toModel(value: Domain): Model
    fun fromModel(value: Model): Domain
}