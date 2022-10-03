package com.example.ceilingmeasurer.data.mapper

import com.example.ceilingmeasurer.data.room.tables.MaterialEntity
import com.example.ceilingmeasurer.domain.DomainToModelMapper
import com.example.ceilingmeasurer.domain.entities.Material

class MaterialMapper : DomainToModelMapper<Material, MaterialEntity> {
    override fun toModel(value: Material): MaterialEntity {
        return MaterialEntity(
            id = value.id,
            name_material = value.name_material,
            unit_measure = value.unit_measure,
            unit_price = value.unit_price,
            unit_work_price = value.unit_work_price,
        )
    }

    override fun fromModel(value: MaterialEntity): Material {
        return Material(
            id = value.id,
            name_material = value.name_material,
            unit_measure = value.unit_measure,
            unit_price = value.unit_price,
            unit_work_price = value.unit_work_price,
        )
    }
}