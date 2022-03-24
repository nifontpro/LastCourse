package ru.nifontbus.les1_data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.nifontbus.les1_data.db.SpotDao
import ru.nifontbus.les1_data.model.toSpot
import ru.nifontbus.les1_data.model.toSpotEntity
import ru.nifontbus.les1_domain.model.Spot
import ru.nifontbus.les1_domain.repository.SpotRepository

class SpotRepositoryImpl(
    private val dao: SpotDao
): SpotRepository {

    override suspend fun insertSpot(spot: Spot) {
        dao.insertSpot(spot.toSpotEntity())
    }

    override suspend fun deleteSpot(spot: Spot) {
        dao.deleteSpot(spot.toSpotEntity())
    }

    override fun getSpots(): Flow<List<Spot>> {
        return dao.getSpots().map { spots ->
            spots.map { it.toSpot() }
        }
    }
}