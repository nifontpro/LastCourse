package ru.nifontbus.les2_data.remote

import retrofit2.http.GET
import retrofit2.http.Query
import ru.nifontbus.les2_data.model.MoviesDto
import ru.nifontbus.les2_data.model.SearchResult

interface MoviesApi {

    @GET("movie/popular")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = "274f828ad283bd634ef4fc1ee4af255f",
        @Query("page") page: Int,
        @Query("language") language: String = "ru-RU"
    ): SearchResult

}