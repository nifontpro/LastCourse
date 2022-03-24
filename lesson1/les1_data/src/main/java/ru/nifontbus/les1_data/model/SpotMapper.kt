package ru.nifontbus.les1_data.model

import ru.nifontbus.les1_domain.model.Spot

fun SpotEntity.toSpot(): Spot {
    return Spot(
        lat = lat,
        lng = lng,
        id = id
    )
}

fun Spot.toSpotEntity(): SpotEntity {
    return SpotEntity(
        lat = lat,
        lng = lng,
        id = id
    )
}