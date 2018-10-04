package com.albertgf.sample.biciapp.domain.location

import android.util.Log
import com.albertgf.sample.biciapp.framework.location.AndroidLocationDataSource
import kotlinx.coroutines.experimental.channels.ReceiveChannel
import kotlinx.coroutines.experimental.channels.SendChannel
import kotlinx.coroutines.experimental.channels.produce
import kotlinx.coroutines.experimental.delay
import javax.inject.Inject

class GetLocationUseCase @Inject constructor(private val androidLocationDataSource: AndroidLocationDataSource) {

     suspend operator fun invoke(channel: SendChannel<Location>) {
         Log.i("usecase","coroutine")
         channel.send(Location(41.390205, 2.154007))
         channel.send(Location(41.390205, 2.154007))
         Log.i("usecase1","coroutine")

    }
}