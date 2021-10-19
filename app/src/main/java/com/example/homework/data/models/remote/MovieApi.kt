package com.example.homework.data.models.remote

import com.example.homework.dataFromAcademy.JsonMovie
import com.example.homework.modelFromAcademy.Movie
import retrofit2.http.GET

interface MovieApi {
    @GET("movie/popular?api_key=822cc7b4b405cf6db0f2b7051939a7ca")
    suspend fun getPopularMovies(): List<MovieItem>
}