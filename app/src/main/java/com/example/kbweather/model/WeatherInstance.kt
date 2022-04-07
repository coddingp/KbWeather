package com.example.kbweather.model

import com.google.gson.annotations.SerializedName

data class WeatherInstance(
    @SerializedName("weather")
    val weather : List<WeatherDetails>
)