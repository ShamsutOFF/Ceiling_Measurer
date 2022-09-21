package com.example.ceilingmeasurer.domain.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Client(
    val id: Int = 0,
    var name: String = "",
    var surname: String = "",
    var phone_number: String = "",
    var address: String = "",
    var district: String = "",
    var status: String = ""
) : Parcelable