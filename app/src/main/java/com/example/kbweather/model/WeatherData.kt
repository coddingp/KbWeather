package com.example.kbweather.model

import com.google.gson.annotations.SerializedName

data class WeatherData(
    val coord: Coord,
    val weather: List<WeatherDetails>,
    val base: String,
    val main: Main,
    val visibility: Long,
    val wind: Wind,
    val clouds: Cloud,
    val dt: Long,
    val sys: Sys,
    val timezone: Int,
    val id: Int,
    val name: String,
    val code: Int
)