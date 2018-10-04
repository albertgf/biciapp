package com.albertgf.sample.biciapp.framework.location

import android.Manifest
import android.app.Activity
import android.content.Context
import android.support.annotation.NonNull
import android.support.annotation.RequiresPermission
import com.albertgf.sample.biciapp.data.location.DeviceLocationSource
import com.albertgf.sample.biciapp.domain.common.DomainError
import com.albertgf.sample.biciapp.domain.common.NotInternetDomainError
import com.albertgf.sample.biciapp.domain.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import org.funktionale.either.Either
import javax.inject.Inject
import kotlin.coroutines.experimental.Continuation
import kotlin.coroutines.experimental.suspendCoroutine

class AndroidLocationDataSource @Inject constructor(private val context: Context) : DeviceLocationSource  {

    var fusedLocationProviderClient : FusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)


    @RequiresPermission(Manifest.permission.ACCESS_FINE_LOCATION)
    @SuppressWarnings("MissingPermission")
    @NonNull
    override
    suspend fun getDeviceLocation(): Location {
        lateinit var result: Continuation<Location>

        fusedLocationProviderClient
                .lastLocation
                .addOnSuccessListener { task ->
                    if(task != null) {
                        result.resume(Location(task.longitude, task.latitude))
                    } else {
                        result.resumeWithException(Throwable())
                    }
                }

        return suspendCoroutine {continuation -> result = continuation}
    }
}