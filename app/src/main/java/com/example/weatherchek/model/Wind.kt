package com.example.weatherchek.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Wind(
    @SerializedName("deg")
    val deg: Int? = null,
    @SerializedName("speed")
    val speed: Double? = null
):Parcelable