package com.example.kbweather.model

import com.google.gson.annotations.SerializedName

data class Visibility(
    @SerializedName("visibility")
    val visibility: Int
)