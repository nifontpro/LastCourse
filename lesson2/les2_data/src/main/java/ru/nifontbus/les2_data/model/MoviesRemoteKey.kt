package ru.nifontbus.les2_data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MoviesRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)
