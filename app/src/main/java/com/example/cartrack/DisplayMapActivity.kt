package com.example.cartrack

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentActivity
import com.example.cartrack.Common.Common.userSelected
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class DisplayMapActivity : FragmentActivity(), OnMapReadyCallback {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_display_map)
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.fragment_map) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        val lat = userSelected!!.address!!.geo!!.lat!!
        val lng = userSelected!!.address!!.geo!!.lng!!

        Log.d("Location of lat", lat.toString())
        Log.d("Location of lng", lng.toString())

        val latLng = LatLng(lat, lng)
        googleMap.addMarker(MarkerOptions().position(latLng).title("Your Address"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng))
        googleMap.uiSettings.isZoomControlsEnabled = true
        val cameraPosition = CameraPosition.Builder()
                .target(latLng) //                .zoom(16)
                .bearing(0f)
                .tilt(45f)
                .build()
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
    }
}