package com.example.kbweather.model

import com.google.gson.annotations.SerializedName

data class TimeZone(
    @SerializedName("timezone")
    val timezone: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("cod")
    val cod: Int
)
