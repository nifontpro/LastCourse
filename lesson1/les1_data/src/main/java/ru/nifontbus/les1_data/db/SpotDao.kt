package ru.nifontbus.les1_data.db

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import ru.nifontbus.les1_data.model.SpotEntity

@Dao
interface SpotDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSpot(spot: SpotEntity)

    @Delete
    suspend fun deleteSpot(spot: SpotEntity)

    @Query("SELECT * FROM spotentity")
    fun getSpots(): Flow<List<SpotEntity>>
}