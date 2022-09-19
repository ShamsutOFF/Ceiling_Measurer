package com.example.ceilingmeasurer.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ceiling(
    val name: String = "",
    val name_material: String = "",
    val length: Double = 0.0,
    val width: Double = 0.0,
    val chandeliers: Int = 0,
    val lamps: Int = 0,
    val corners: Int = 0,
    val stroke: Int = 0,
    val two_steps: Double = 0.0,
    val curtain: Double = 0.0,
    val alu_curtain: Double = 0.0,
    val price_for_m2: Double = 0.0,
    val attachment: String = "",
) : Parcelable