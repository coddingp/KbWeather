package com.example.kbweather

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.kbweather.api.RetrofitClient
import com.example.kbweather.api.WeatherApi
import com.example.kbweather.databinding.ActivityMainBinding
import com.example.kbweather.model.WeatherData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

private const val API_KEY = "7f3c4dca8bafcb34ed54d37e04c089e6"
class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val api: WeatherApi = RetrofitClient.getRetrofit().create(WeatherApi::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        with(binding) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_main)
            api.getWeatherData("Bishkek", API_KEY).enqueue(object : Callback<WeatherData> {
                override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                    if (response.isSuccessful && response.body() != null) {
                        val data = response.body()!!
                        Log.e("!!!", "{${data.name}}")
                        Log.e("!!!", "{${data.main.temp}}")
                        val city = data.name
                        var temp = (data.main.temp - 32) * 0.5555556
                        cityTextView.text= city
                        temperatureTextView.text = temp.toString()
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