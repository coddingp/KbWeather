package com.example.kbweather

import com.example.kbweather.model.WeatherData

interface WeatherContract {
    fun showWeatherData(data: WeatherData)
    fun onDataFailure(message: String)
}