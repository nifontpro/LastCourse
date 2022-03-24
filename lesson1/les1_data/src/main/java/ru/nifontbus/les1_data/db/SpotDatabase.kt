package ru.nifontbus.les1_data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.nifontbus.les1_data.model.SpotEntity

@Database(
    entities = [SpotEntity::class],
    version = 1
)
abstract class SpotDatabase: RoomDatabase() {

    abstract val dao: SpotDao
}