package com.example.pswtestapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.getDrawable
import androidx.fragment.app.Fragment
import com.example.pswtestapp.R
import com.example.pswtestapp.view.MainActivity
import com.utsman.osmandcompose.Marker
import com.utsman.osmandcompose.OpenStreetMap
import com.utsman.osmandcompose.Polyline
import com.utsman.osmandcompose.rememberCameraState
import com.utsman.osmandcompose.rememberMarkerState
import org.osmdroid.util.GeoPoint

class MainFragment: Fragment() {

    private val zoomLevel = 12.0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                val isFirstMarkerSet = remember { mutableStateOf(false) }
                val isSecondMarkerSet = remember { mutableStateOf(false) }
                val centeringState = remember { mutableStateOf(false) }
                val focusState = remember { mutableStateOf(false) }
                val firstMarkerState = rememberMarkerState(geoPoint = GeoPoint(-6.3970066, 106.8224316))
                val secondMarkerState = rememberMarkerState(geoPoint = GeoPoint(-6.3970066, 106.8224316))
                val cameraState = rememberCameraState {
                    geoPoint = GeoPoint(55.782949, 37.605309)
                }
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    //color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        OpenStreetMap(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(3f)
                                .background(Color.Yellow),
                            cameraState = cameraState,
                            onMapClick = { geopoint ->
                                if (!focusState.value) {
                                    if (!isFirstMarkerSet.value) { isFirstMarkerSet.value = true }

                                    if (isFirstMarkerSet.value && !centeringState.value) {
                                        firstMarkerState.geoPoint = geopoint
                                        cameraState.geoPoint = geopoint
                                        cameraState.animateTo(cameraState.geoPoint, zoomLevel)
                                        cameraState.zoom = zoomLevel
                                    }

                                    if (centeringState.value) {
                                        if (!isSecondMarkerSet.value) { isSecondMarkerSet.value = true }
                                        secondMarkerState.geoPoint = geopoint
                                    }
                                }
                            }
                        ) {
                            if (centeringState.value && isSecondMarkerSet.value) {
                                Polyline(geoPoints = listOf(firstMarkerState.geoPoint, secondMarkerState.geoPoint))
                            }
                            Marker(
                                state = firstMarkerState,
                                icon = getDrawable(requireContext(), R.drawable.baseline_store_48),
                                visible = isFirstMarkerSet.value
                            )
                            Marker(
                                state = secondMarkerState,
                                icon = getDrawable(requireContext(), R.drawable.baseline_add_location_48),
                                visible = centeringState.value && isSecondMarkerSet.value
                            )
                        }
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .weight(2f)
                            .background(Color.White)
                            .padding(start = 5.dp, top = 5.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(
                                    checked = centeringState.value,
                                    onCheckedChange = {
                                        if (isFirstMarkerSet.value && !focusState.value) {
                                            centeringState.value = it
                                        } else {
                                            Toast.makeText(requireContext(), getString(R.string.cannot_select), Toast.LENGTH_SHORT).show()
                                        }

                                        if (!centeringState.value) {
                                            isSecondMarkerSet.value = false
                                        }
                                    })
                                Text(text = getString(R.string.centering))
                            }
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Checkbox(
                                    checked = focusState.value,
                                    onCheckedChange = {
                                        if (centeringState.value && isSecondMarkerSet.value) {
                                            focusState.value = it
                                        } else {
                                            Toast.makeText(requireContext(), getString(R.string.cannot_select), Toast.LENGTH_SHORT).show()
                                        }
                                    })
                                Text(text = getString(R.string.focus))
                            }
                            Row() {
                                Button(onClick = {
                                    (activity as MainActivity).openMenuFragment()
                                }) {
                                    Text(text = getString(R.string.menu))
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}