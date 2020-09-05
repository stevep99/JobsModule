package demo.jobslist.fragments

import android.content.Context
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import demo.jobslist.viewmodel.JobViewModel


class GoogleMapFragment : SupportMapFragment(), OnMapReadyCallback {

    private lateinit var viewModel: JobViewModel
    private lateinit var googleMap: GoogleMap

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(JobViewModel::class.java)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        this.googleMap = googleMap

        googleMap.mapType = GoogleMap.MAP_TYPE_NORMAL
        googleMap.setMaxZoomPreference(18f)
        googleMap.setMinZoomPreference(8f)

        centreMap()
    }

    fun centreMap() {
        val currentJobItem = viewModel.currentJobItem
        if (currentJobItem != null) {
            val centerLatLng = LatLng(currentJobItem.lat, currentJobItem.lng);
            val cameraPosition = CameraPosition.Builder()
                .target(centerLatLng)
                .zoom(15f)
                .build()
            googleMap.animateCamera(
                CameraUpdateFactory.newCameraPosition(cameraPosition),
                500,
                null
            )

            googleMap.addMarker(
                MarkerOptions()
                    .position(centerLatLng)
                    .title(currentJobItem.title)
            )

        }
    }

}