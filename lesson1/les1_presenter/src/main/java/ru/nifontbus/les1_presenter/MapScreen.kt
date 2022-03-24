package ru.nifontbus.les1_presenter

import android.Manifest
import android.content.pm.PackageManager
import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.core.app.ActivityCompat
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import ru.nifontbus.core_ui.permission.GetPermission

@ExperimentalPermissionsApi

@Composable
fun MapScreen() {
    GetPermission(
        Manifest.permission.ACCESS_FINE_LOCATION,
        stringResource(R.string.sLocatePermission)
    ) {
        MapMainScreen()
    }
}

@Composable
fun MapMainScreen() {

    val viewModel: MapViewModel = hiltViewModel()
    val scaffoldState = rememberScaffoldState()

    val uiSettings = remember {
        MapUiSettings(zoomControlsEnabled = false)
    }

    GetLocation()

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colors.background,
    ) {

        val spots = viewModel.spots.collectAsState(initial = emptyList()).value

        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            uiSettings = uiSettings,
            onMapLongClick = {
                viewModel.insertSpot(it)
            }
        ) {
            spots.forEach { spot ->
                Marker(
                    state = MarkerState(LatLng(spot.lat, spot.lng)),
                    title = stringResource(R.string.sSpotInfo, spot.lat, spot.lng),
                    snippet = stringResource(R.string.sLongTap),
                    onInfoWindowLongClick = {
                        viewModel.deleteSpot(spot = spot)
                    },
                    onClick = {
                        it.showInfoWindow()
                        true
                    },
                    icon = BitmapDescriptorFactory.defaultMarker(
                        BitmapDescriptorFactory.HUE_CYAN
                    )
                )
            }
        }
    }
}

@Composable
private fun GetLocation() {
    val context = LocalContext.current
    LaunchedEffect(key1 = Unit) {

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED || ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                location?.let {
                    Log.e("my", "lat: ${it.latitude}")
                    Log.e("my", "long: ${it.longitude}")
                }
            }
        }
    }
}