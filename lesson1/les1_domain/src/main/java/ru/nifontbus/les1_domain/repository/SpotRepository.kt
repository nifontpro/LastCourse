package ru.nifontbus.les1_domain.repository

import kotlinx.coroutines.flow.Flow
import ru.nifontbus.les1_domain.model.Spot

interface SpotRepository {

    suspend fun insertSpot(spot: Spot)

    suspend fun deleteSpot(spot: Spot)

    fun getSpots(): Flow<List<Spot>>
}