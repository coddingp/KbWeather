package com.example.kbweather

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.kbweather.api.RetrofitClient
import com.example.kbweather.api.WeatherApi
import com.example.kbweather.databinding.ActivityMainBinding
import com.example.kbweather.model.WeatherData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber


private const val API_KEY = "7f3c4dca8bafcb34ed54d37e04c089e6"


class MainActivity : AppCompatActivity() {
    var cityName = "Kara-Balta"
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val api: WeatherApi = RetrofitClient.getRetrofit().create(WeatherApi::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.plant(Timber.DebugTree())

        // integer
        val a = 100
        Timber.d("Integer a value is: %d", a)

        val name = "Android Studio"
        Timber.d("My name is: %s", name)

        with(binding) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)

            api.getWeatherData("$cityName", API_KEY, "metric", "eng")
                .enqueue(object : Callback<WeatherData> {
                    @SuppressLint("SetTextI18n")
                    override fun onResponse(
                        call: Call<WeatherData>,
                        response: Response<WeatherData>
                    ) {

                        if (response.isSuccessful && response.body() != null) {
                            val data = response.body()!!
                            Timber.e( "{${data.name}}")
                            Log.e("!!!", "{${data.main.temp}}")
                            val city = data.name
                            val temp = data.main.temp
                            val humidity = data.main.humidity
                            val wind = data.wind.speed
                            if (data.weather[0] != null) {
                                val clouds = data.weather[0].description
                                cloudsTextView.text = clouds.toString()
                            }
                            windTextView.text = "$wind m/s"
                            cityTextView.text = city
                            temperatureTextView.text = "$temp °c"
                            "Humidity: $humidity".also { humidityTextView.text = it }

                            setContentView(root)
                        }
                    }

                    override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                        Log.e("$$$", "${t.message}")
                    }
                })
        }
    }

    fun onClick(view: View) {
        binding.apply {
            changeCityButtonView.setOnClickListener {
                api.getWeatherData("${editCityTextView.text}", API_KEY, "metric", "eng")
                    .enqueue(object : Callback<WeatherData> {
                        override fun onResponse(
                            call: Call<WeatherData>,
                            response: Response<WeatherData>
                        ) {
                            if (response.isSuccessful && response.body() != null) {
                                val data = response.body()!!
                                Log.e("!!!", "{${data.name}}")
                                Log.e("!!!", "{${data.main.temp}}")
                                val city = data.name
                                val temp = data.main.temp
                                val humidity = data.main.humidity
                                val wind = data.wind.speed
                                if (data.weather[0] != null) {
                                    val clouds = data.weather[0].description
                                    cloudsTextView.text = clouds.toString()
                                }
                                windTextView.text = "$wind m/s"
                                cityTextView.text = city
                                temperatureTextView.text = "$temp °c"
                                "Humidity: $humidity".also { humidityTextView.text = it }
                                setContentView(root)
                            }
                        }

                        override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                            Log.e("$$$", "${t.message}")
                        }
                    })
            }
        }
    }
}