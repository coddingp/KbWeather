package com.example.kbweather.model

import com.google.gson.annotations.SerializedName

data class Base(
    @SerializedName("base")
    val base: String
)
