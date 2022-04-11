package com.example.kbweather

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.kbweather.api.RetrofitClient
import com.example.kbweather.api.WeatherApi
import com.example.kbweather.databinding.ActivityMainBinding
import com.example.kbweather.model.WeatherData


private const val API_KEY = "7f3c4dca8bafcb34ed54d37e04c089e6"


class MainActivity : AppCompatActivity(), WeatherContract {
    var cityName = "Kara-Balta"
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private  val api: WeatherApi = RetrofitClient.getRetrofit().create(WeatherApi::class.java)
    private val presenter: WeatherPresenter = WeatherPresenter(api)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter.attach(this)
        binding.apply{
            var city = editCityTextView.text.toString()
            presenter.getWeatherData(city, API_KEY)
        }
    }

    override fun showWeatherData(data: WeatherData)=with(binding) {
        temperatureTextView.text = data.main.temp.toString()
        humidityTextView.text = data.main.humidity.toString()
        windTextView.text = data.wind.toString()
    }

    override fun onDataFailure(message: String) {
        error(message)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detach(this)
    }

}