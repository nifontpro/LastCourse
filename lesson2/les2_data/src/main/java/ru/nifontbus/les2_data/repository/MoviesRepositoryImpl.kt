package ru.nifontbus.les2_data.repository

import androidx.paging.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.nifontbus.les2_data.local.MoviesDatabase
import ru.nifontbus.les2_data.paging.MoviesRemoteMediator
import ru.nifontbus.les2_data.paging.SearchPagingSource
import ru.nifontbus.les2_data.remote.MoviesApi
import ru.nifontbus.les2_data.util.Constants.ITEMS_PER_PAGE
import ru.nifontbus.les2_domain.model.Movie
import ru.nifontbus.les2_domain.repository.MoviesRepository
import javax.inject.Inject

@ExperimentalPagingApi
class MoviesRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi,
    private val moviesDatabase: MoviesDatabase
) : MoviesRepository {

    override fun getMovies(): Flow<PagingData<Movie>> {
        val pagingSourceFactory = { moviesDatabase.moviesDao().getMovies() }
        return Pager(
            config = PagingConfig(
                pageSize = ITEMS_PER_PAGE,
            ),
            remoteMediator = MoviesRemoteMediator(
                moviesApi = moviesApi,
                moviesDatabase = moviesDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow.map { pagingData ->
            pagingData.map { it.toMovie() }
        }
    }

    override fun searchMovies(): Flow<PagingData<Movie>> {
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            pagingSourceFactory = {
                SearchPagingSource(unsplashApi = moviesApi)
            }
        ).flow.map { pagingData ->
            pagingData.map { it.toMovie() }
        }
    }
}