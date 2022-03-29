package com.example.kbweather.model

import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("feelsLike")
    val feelsLike: Double,
    @SerializedName("tempMin")
    val tempMin: Double,
    @SerializedName("tempMax")
    val tempMax: Double,
    @SerializedName("pressure")
    val pressure: Double,
    @SerializedName("humidity")
    val humidity: Int
)
