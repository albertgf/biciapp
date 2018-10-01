package com.albertgf.sample.biciapp.ui.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.albertgf.sample.biciapp.BiciApp
import com.albertgf.sample.biciapp.R
import com.albertgf.sample.biciapp.domain.common.DomainError
import com.albertgf.sample.biciapp.domain.stations.StationMinimalDomain
import com.albertgf.sample.biciapp.ui.presenter.MainPresenter
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import javax.inject.Inject
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.LatLng



class MainActivity : BaseActivity(), MainPresenter.View, OnMapReadyCallback {


    override val layoutId: Int = R.layout.activity_main

    @Inject lateinit var presenter: MainPresenter

    lateinit var map : GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        BiciApp.component.inject(this)

        val mapFragment : SupportMapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onStart() {
        super.onStart()

        presenter.attachView(this)
    }

    override fun onResume() {
        super.onResume()

        presenter.update()
    }

    override fun onStop() {
        super.onStop()

        presenter.deattachView()
    }

    override fun onMapReady(googleMap: GoogleMap?) {
        map = googleMap ?: return

        // Add a marker in Sydney, Australia, and move the camera.
        val barcelona = LatLng(41.390205, 2.154007)
        map.addMarker(MarkerOptions().position(barcelona).title("Marker in barcelona"))
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(barcelona, 12f))
    }

    override fun hideLoadind() {
        Log.d("main","hide loading")
    }

    override fun showLoading() {
        Log.d("main","showloading")
    }

    override fun showDomainError(error: DomainError) {
        Log.d("main","show domain error")
    }

    override fun showEmptyCase() {
        Log.d("main","empty")
    }

    override fun showStations(list: List<MarkerOptions>) {
        list.forEach {
            map.addMarker(it)
        }
    }
}
