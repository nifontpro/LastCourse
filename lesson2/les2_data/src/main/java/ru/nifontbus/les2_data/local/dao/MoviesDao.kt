package ru.nifontbus.les2_data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.nifontbus.les2_data.model.MoviesDto

@Dao
interface MoviesDao {

    @Query("SELECT * FROM MoviesDto")
    fun getMovies(): PagingSource<Int, MoviesDto>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(movies: List<MoviesDto>)

    @Query("DELETE FROM MoviesDto")
    suspend fun deleteAllMovies()

}