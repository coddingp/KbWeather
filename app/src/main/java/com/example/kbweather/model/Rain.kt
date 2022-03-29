package com.example.kbweather.model

import com.google.gson.annotations.SerializedName

data class Rain(
    @SerializedName("hour")
    val hour: Double
)