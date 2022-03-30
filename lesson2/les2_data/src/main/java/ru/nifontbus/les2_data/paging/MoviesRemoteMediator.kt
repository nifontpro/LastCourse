package ru.nifontbus.les2_data.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import ru.nifontbus.les2_data.local.MoviesDatabase
import ru.nifontbus.les2_data.model.MoviesDto
import ru.nifontbus.les2_data.model.MoviesRemoteKey
import ru.nifontbus.les2_data.remote.MoviesApi

@ExperimentalPagingApi
class MoviesRemoteMediator (
    private val moviesApi: MoviesApi,
    private val moviesDatabase: MoviesDatabase
) : RemoteMediator<Int, MoviesDto>() {

    private val moviesDao = moviesDatabase.moviesDao()
    private val moviesRemoteKeysDao =  moviesDatabase.moviesRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, MoviesDto>
    ): MediatorResult {
        return try {
            val currentPage = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = moviesApi.getMovies(page = currentPage).movies
            val endOfPaginationReached = response.isEmpty()

            Log.e("my", "endOfPaginationReached = $endOfPaginationReached")
            Log.e("my", "currentPage = $currentPage")

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            moviesDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    moviesDao.deleteAllMovies()
                    moviesRemoteKeysDao.deleteAllRemoteKeys()
                }

                val keys = response.map { unsplashImage ->
                    MoviesRemoteKey(
                        id = unsplashImage.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                moviesRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                moviesDao.addMovies(movies = response)
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    /**
     * Получить удаленный ключ, ближайший к текущей позиции
     */
    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, MoviesDto>
    ): MoviesRemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                moviesRemoteKeysDao.getRemoteKey(id = id)
            }
        }
    }

    /**
     * Получить удаленный ключ для первого элемента
     */
    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, MoviesDto>
    ): MoviesRemoteKey? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { unsplashImage ->
                moviesRemoteKeysDao.getRemoteKey(id = unsplashImage.id)
            }
    }

    /**
     * Получить удаленный ключ для последнего элемента
     */
    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, MoviesDto>
    ): MoviesRemoteKey? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { unsplashImage ->
                moviesRemoteKeysDao.getRemoteKey(id = unsplashImage.id)
            }
    }
}