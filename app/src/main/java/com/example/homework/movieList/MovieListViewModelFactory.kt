package com.example.homework.movieList

import android.app.Application
import android.content.Context
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.homework.dataFromAcademy.JsonMovieRepository

class MovieListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T = when (modelClass) {
        MovieListViewModel::class.java -> MovieListViewModel(JsonMovieRepository(context = context))
        else -> throw IllegalArgumentException("$modelClass is not registered ViewModel")
    } as T

}