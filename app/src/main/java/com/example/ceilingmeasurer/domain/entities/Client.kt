package com.example.ceilingmeasurer.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Client(
    val id: Int?,
    val name: String = "",
    val surname: String = "",
    val phone_number: String = "",
    val address: String = "",
    val district: String = "",
    val status: String = ""
) : Parcelable