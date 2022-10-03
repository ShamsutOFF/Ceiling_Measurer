package com.example.ceilingmeasurer.data.mapper

import com.example.ceilingmeasurer.data.room.tables.CeilingEntity
import com.example.ceilingmeasurer.domain.DomainToModelMapper
import com.example.ceilingmeasurer.domain.entities.Ceiling

class CeilingMapper : DomainToModelMapper<Ceiling, CeilingEntity> {
    override fun toModel(value: Ceiling): CeilingEntity {
        return CeilingEntity(
            ceilingId = value.id,
            clientId = value.clientId,
            name = value.name,
            name_material = value.name_material,
            length = value.length,
            width = value.width,
            chandeliers = value.chandeliers,
            lamps = value.lamps,
            corners = value.corners,
            stroke = value.stroke,
            two_steps = value.two_steps,
            curtain = value.curtain,
            alu_curtain = value.alu_curtain,
            price_for_m2 = value.price_for_m2,
            attachment = value.attachment,
        )
    }

    override fun fromModel(value: CeilingEntity): Ceiling {
        return Ceiling(
            id = value.ceilingId,
            clientId = value.clientId,
            name = value.name,
            name_material = value.name_material,
            length = value.length,
            width = value.width,
            chandeliers = value.chandeliers,
            lamps = value.lamps,
            corners = value.corners,
            stroke = value.stroke,
            two_steps = value.two_steps,
            curtain = value.curtain,
            alu_curtain = value.alu_curtain,
            price_for_m2 = value.price_for_m2,
            attachment = value.attachment,
        )
    }
}