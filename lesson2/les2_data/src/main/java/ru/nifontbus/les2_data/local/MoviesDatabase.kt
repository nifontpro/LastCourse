package ru.nifontbus.les2_data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.nifontbus.les2_data.local.dao.MoviesDao
import ru.nifontbus.les2_data.local.dao.MoviesRemoteKeysDao
import ru.nifontbus.les2_data.model.MoviesRemoteKey
import ru.nifontbus.les2_data.model.MoviesDto

@Database(entities = [MoviesDto::class, MoviesRemoteKey::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {

    abstract fun moviesDao(): MoviesDao
    abstract fun moviesRemoteKeysDao(): MoviesRemoteKeysDao

}