package com.example.kbweather

import com.example.kbweather.api.WeatherApi
import com.example.kbweather.model.WeatherData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val API_KEY = "7f3c4dca8bafcb34ed54d37e04c089e6"

class WeatherPresenter(private val api: WeatherApi) {
    private var weatherContract: WeatherContract? = null

    fun attach(view: WeatherContract) {
        weatherContract = view
    }

    fun detach(view: WeatherContract) {
        weatherContract = null
    }

    fun getWeatherData(city: String, apId: String) {
        api.getWeatherData("Bishkek", API_KEY, "metric", "eng")
            .enqueue(object : Callback<WeatherData> {
                override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                    if (response.isSuccessful && response.body() != null) {
                        val data = response.body()!!
                        weatherContract?.showWeatherData(data)
                    }
                }

                override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                    t.message?.let { weatherContract?.onDataFailure(it) }
                }

            })
    }

}
