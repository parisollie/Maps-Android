package com.pjff.mapas.views

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.pjff.mapas.models.LocationModel
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState

@Composable
fun MapsView() {

    //Vid 271
    val markers = listOf(
        LocationModel("Times Square", LatLng(40.758896, -73.985130)),
        LocationModel("Central Park", LatLng(40.785091, -73.968285)),
        LocationModel("Grand Central Terminal", LatLng(40.752726, -73.977229)),
        LocationModel("Estatua de la Libertad", LatLng(40.689247, -74.044502))

    )

    //vid 269
   // val newYork = LatLng(40.758896, -73.985130)
    //Vid 270
    //val markerState = rememberMarkerState(position = newYork)
    val cameraPosition = CameraPosition.fromLatLngZoom(markers.first().coordinates, 10f)
    val cameraState = rememberCameraPositionState { position = cameraPosition}
    var mapLoading by remember { mutableStateOf(true) }
    Box(modifier = Modifier.fillMaxSize()) {
        GoogleMap(
            modifier = Modifier.matchParentSize(),
            cameraPositionState = cameraState,
            onMapLoaded = {
                mapLoading = false
            }
        ){
            //Vids 271
            markers.forEach{ mark ->
                Marker(
                    state = rememberMarkerState(position = mark.coordinates),
                    title = mark.name
                )
            }
        }
        if(mapLoading){
            AnimatedVisibility(
                visible = mapLoading,
                modifier = Modifier.matchParentSize(),
                enter = EnterTransition.None,
                exit = fadeOut()
                ) {
                CircularProgressIndicator(
                    modifier = Modifier.wrapContentSize()
                )
            }
        }
    }
}