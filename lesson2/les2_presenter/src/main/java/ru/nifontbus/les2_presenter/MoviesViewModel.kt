package ru.nifontbus.les2_presenter

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.nifontbus.les2_domain.repository.MoviesRepository
import javax.inject.Inject

@ExperimentalPagingApi

@HiltViewModel
class MoviesViewModel @Inject constructor(
    moviesRepository: MoviesRepository
) : ViewModel() {
//    val movies = moviesRepository.getMovies()
    val movies = moviesRepository.searchMovies()
}