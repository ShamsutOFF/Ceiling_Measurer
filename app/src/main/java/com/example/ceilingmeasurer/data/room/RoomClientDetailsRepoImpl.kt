package com.example.ceilingmeasurer.data.room

import com.example.ceilingmeasurer.data.room.dao.CeilingDAO
import com.example.ceilingmeasurer.data.room.tables.CeilingEntity
import com.example.ceilingmeasurer.domain.ClientDetailsRepo
import com.example.ceilingmeasurer.domain.entities.Ceiling

class RoomClientDetailsRepoImpl(private val dao: CeilingDAO) : ClientDetailsRepo {
    override suspend fun getCeilings(clientId: Int): List<Ceiling> {
        val tempList = dao.getAllCeilingsByClientId(clientId)
        val returnList = mutableListOf<Ceiling>()
        for (ceilingEntity in tempList) {
            returnList.add(
                Ceiling(
                    id = ceilingEntity.ceilingId,
                    clientId = ceilingEntity.clientId,
                    name = ceilingEntity.name,
                    name_material = ceilingEntity.name_material,
                    length = ceilingEntity.length,
                    width = ceilingEntity.width,
                    chandeliers = ceilingEntity.chandeliers,
                    lamps = ceilingEntity.lamps,
                    corners = ceilingEntity.corners,
                    stroke = ceilingEntity.stroke,
                    two_steps = ceilingEntity.two_steps,
                    curtain = ceilingEntity.curtain,
                    alu_curtain = ceilingEntity.alu_curtain,
                    price_for_m2 = ceilingEntity.price_for_m2,
                    attachment = ceilingEntity.attachment,
                )
            )
        }
        return returnList
    }

    override suspend fun saveCeilings(ceilingList: List<Ceiling>) {
        val insertList = mutableListOf<CeilingEntity>()
        for (ceiling in ceilingList) {
            insertList.add(
                CeilingEntity(
                    ceilingId = ceiling.id ?: 0,
                    clientId = ceiling.clientId ?: 0,
                    name = ceiling.name,
                    name_material = ceiling.name_material,
                    length = ceiling.length,
                    width = ceiling.width,
                    chandeliers = ceiling.chandeliers,
                    lamps = ceiling.lamps,
                    corners = ceiling.corners,
                    stroke = ceiling.stroke,
                    two_steps = ceiling.two_steps,
                    curtain = ceiling.curtain,
                    alu_curtain = ceiling.alu_curtain,
                    price_for_m2 = ceiling.price_for_m2,
                    attachment = ceiling.attachment,
                )
            )
        }
        for (ceiling in insertList) {
            dao.insertNewCeiling(ceiling)
        }
    }

    override suspend fun deleteCeiling(ceiling: Ceiling) {
        dao.deleteCeiling(
            CeilingEntity(
                ceilingId = ceiling.id ?: 0,
                clientId = ceiling.clientId ?: 0,
                name = ceiling.name,
                name_material = ceiling.name_material,
                length = ceiling.length,
                width = ceiling.width,
                chandeliers = ceiling.chandeliers,
                lamps = ceiling.lamps,
                corners = ceiling.corners,
                stroke = ceiling.stroke,
                two_steps = ceiling.two_steps,
                curtain = ceiling.curtain,
                alu_curtain = ceiling.alu_curtain,
                price_for_m2 = ceiling.price_for_m2,
                attachment = ceiling.attachment,
            )
        )
    }
}