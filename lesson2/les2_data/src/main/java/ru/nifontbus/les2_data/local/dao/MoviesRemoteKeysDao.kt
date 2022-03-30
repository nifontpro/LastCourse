package ru.nifontbus.les2_data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ru.nifontbus.les2_data.model.MoviesRemoteKey

@Dao
interface MoviesRemoteKeysDao {

    @Query("SELECT * FROM MoviesRemoteKey WHERE id =:id")
    suspend fun getRemoteKey(id: Int): MoviesRemoteKey

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<MoviesRemoteKey>)

    @Query("DELETE FROM MoviesRemoteKey")
    suspend fun deleteAllRemoteKeys()

}