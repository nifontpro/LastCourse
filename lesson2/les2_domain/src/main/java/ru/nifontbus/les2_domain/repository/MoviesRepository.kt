package ru.nifontbus.les2_domain.repository

import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import ru.nifontbus.les2_domain.model.Movie

interface MoviesRepository  {
    fun getMovies(): Flow<PagingData<Movie>> // С хэшированием
    fun searchMovies(): Flow<PagingData<Movie>> // Без хэширования
}