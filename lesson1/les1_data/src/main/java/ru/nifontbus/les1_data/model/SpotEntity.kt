package ru.nifontbus.les1_data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SpotEntity(
    val lat: Double,
    val lng: Double,
    @PrimaryKey val id: Int? = null
)
