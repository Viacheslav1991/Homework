package com.example.homework.movieList

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.homework.dataFromAcademy.MovieRepository
import com.example.homework.modelFromAcademy.Movie
import kotlinx.coroutines.launch

class MovieListViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private val _mutableMoviesList = MutableLiveData<List<Movie>>(emptyList())

    val moviesList: LiveData<List<Movie>> get() = _mutableMoviesList

    fun loadMovies() {
        viewModelScope.launch {
            val movieList = movieRepository.loadMovies()
            _mutableMoviesList.value = movieList.orEmpty()
        }
    }
}