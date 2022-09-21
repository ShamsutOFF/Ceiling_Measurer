package com.example.ceilingmeasurer.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Ceiling(
    var id: Int?,
    var clientId: Int?,
    var name: String = "",
    var name_material: String = "",
    var length: Double = 0.0,
    var width: Double = 0.0,
    var chandeliers: Int = 0,
    var lamps: Int = 0,
    var corners: Int = 0,
    var stroke: Int = 0,
    var two_steps: Double = 0.0,
    var curtain: Double = 0.0,
    var alu_curtain: Double = 0.0,
    var price_for_m2: Double = 0.0,
    var attachment: String = "",
) : Parcelable