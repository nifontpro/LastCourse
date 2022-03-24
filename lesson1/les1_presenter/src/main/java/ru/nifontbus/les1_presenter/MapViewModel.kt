package ru.nifontbus.les1_presenter

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.nifontbus.les1_domain.model.Spot
import ru.nifontbus.les1_domain.repository.SpotRepository
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val repository: SpotRepository
) : ViewModel() {

    val spots = repository.getSpots()

    fun insertSpot(latLng: LatLng) = viewModelScope.launch {
        repository.insertSpot(Spot(lat = latLng.latitude, lng = latLng.longitude))
    }

    fun deleteSpot(spot: Spot) = viewModelScope.launch {
        repository.deleteSpot(spot)
    }
}