package com.example.ceilingmeasurer.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Material(
    val id: Int = 0,
    val name_material: String = "Новый материал",
    val unit_measure: String = "м2",
    val unit_price: Double = 260.0,
    val unit_work_price: Double = 360.0
) : Parcelable