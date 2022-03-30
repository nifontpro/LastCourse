package ru.nifontbus.les2_data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.nifontbus.les2_data.model.MoviesDto
import ru.nifontbus.les2_data.remote.MoviesApi

class SearchPagingSource(
    private val unsplashApi: MoviesApi,
) : PagingSource<Int, MoviesDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MoviesDto> {
        val currentPage = params.key ?: 1
        return try {
            val response = unsplashApi.getMovies(page = currentPage)
            val endOfPaginationReached = response.movies.isEmpty()
            if (response.movies.isNotEmpty()) {
                LoadResult.Page(
                    data = response.movies,
                    prevKey = if (currentPage > 1) currentPage - 1 else null,
                    nextKey = if (endOfPaginationReached) null else currentPage + 1
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

/*    override fun getRefreshKey(state: PagingState<Int, MoviesDto>): Int? {
        return state.anchorPosition
    }*/

    override fun getRefreshKey(state: PagingState<Int, MoviesDto>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val anchorPage = state.closestPageToPosition(anchorPosition) ?: return null
        return anchorPage.prevKey?.plus(1) ?: anchorPage.nextKey?.minus(1)
    }

}