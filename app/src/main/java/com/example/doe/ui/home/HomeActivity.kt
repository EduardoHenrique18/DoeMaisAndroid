package com.example.doe.ui.home

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.doe.R
import com.example.doe.data.local.local.SharedPreferenceUserDataSource
import com.example.doe.domain.usecase.SearchPointUseCase
import com.example.doe.remote.RestWebService
import com.example.doe.remote.SearchPointService
import com.example.doe.remote.response.CreatePointDetailResponse
import com.example.doe.ui.createPoint.CreatePointActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class HomeActivity : AppCompatActivity(), OnMapReadyCallback, HomeContract.HomeView {

    private lateinit var mMap: GoogleMap
    private lateinit var presenter: HomeContract.HomePresenter
    private var latitude: Double = 0.0
    private var longitude: Double = 0.0

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        val iin = intent
        val extras = iin.extras

        if (extras != null) {
            this.longitude = extras.get("longitude") as Double
            this.latitude = extras.get("latitude") as Double
        }

        presenter = HomePresenter(
            this,
            SearchPointUseCase(
                SearchPointService(RestWebService().api)
            ),
            SharedPreferenceUserDataSource(this)
        )

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                100);
        }

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        presenter.searchPoints()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val markerOptions = MarkerOptions()
        val latLng = LatLng(this.latitude, this.longitude)
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(this.latitude, this.longitude), 16.0f))
        markerOptions.position(latLng)
        mMap.addMarker(markerOptions)

        mMap.setOnMapClickListener { point ->
            var intent = Intent(this, CreatePointActivity::class.java)
            intent.putExtra("latitude", point.latitude)
            intent.putExtra("longitude", point.longitude)
            startActivity(intent)
        }

    }

    override fun updatePoints(points: List<CreatePointDetailResponse>) {
        points.forEach { point ->
            val markerOptions = MarkerOptions()
            val latLng = LatLng(point.latitude.toDouble(), point.longitude.toDouble())
            markerOptions.position(latLng)
            mMap.addMarker(markerOptions)
        }
    }

}