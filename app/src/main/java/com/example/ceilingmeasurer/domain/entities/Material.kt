package com.example.ceilingmeasurer.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Material(
    val name_material: String = "Полотно белый глянец",
    val unit_measure: String = "м2",
    val unit_price: Int = 260,
    val unit_work_price: Int = 360
) : Parcelable