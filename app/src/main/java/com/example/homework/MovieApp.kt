package com.example.homework

import android.app.Application
import com.example.homework.data.models.remote.MovieApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieApp : Application() {

    private val json = Json {
        ignoreUnknownKeys = true
    }
    lateinit var movieApi: MovieApi

    override fun onCreate() {
        super.onCreate()

        configureRetrofit()
    }

    private fun configureRetrofit() {
        val okHttpClient = OkHttpClient.Builder().build()

        val retrofit = Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        movieApi = retrofit.create(MovieApi::class.java)
    }
    companion object {
        private const val BASE_URL = "https://api.themoviedb.org/3/"
    }
}


