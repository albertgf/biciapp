package com.albertgf.sample.biciapp.ui.presenter

import com.albertgf.sample.biciapp.common.optionalLeft
import com.albertgf.sample.biciapp.common.optionalRight
import com.albertgf.sample.biciapp.domain.common.DomainError
import com.albertgf.sample.biciapp.domain.stations.GetStationsUseCase
import com.albertgf.sample.biciapp.domain.stations.StationMinimalDomain
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import org.funktionale.either.Either
import java.lang.Exception
import javax.inject.Inject

class MainPresenter @Inject constructor(private val getStationsUseCase: GetStationsUseCase) {

    private var view: View? = null

    fun update() {
        getStations()
    }

    fun attachView(attachView: View) {
        view = attachView
    }

    fun deattachView() {
        view = null
    }

    private fun getStations() {
        launch(UI) {
            try {
                val result = async(CommonPool) {
                    stationsToMarkers(getStationsUseCase())
                }.await()

                when {
                    result.isRight() -> showStations(result.right().get())
                    else -> view?.showDomainError(result.left().get())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun stationsToMarkers(result: Either<DomainError, List<StationMinimalDomain>>) : Either<DomainError, List<MarkerOptions>> {
        return when {
            result.isRight() -> Either.right(result.right().get().mapIndexed {
                index, stationMinimalDomain ->
                MarkerOptions().position(LatLng(stationMinimalDomain.latitude, stationMinimalDomain.longitude)).title(stationMinimalDomain.name)
            })
            else -> Either.left(result.left().get())
        }

    }

    private fun showStations(result: List<MarkerOptions>) {
        when {
            result.isEmpty() -> view?.showEmptyCase()
            else -> view?.showStations(result)
        }
    }

    interface View {
        fun hideLoadind()
        fun showLoading()
        fun showDomainError(error: DomainError)
        fun showEmptyCase()
        fun showStations(list: List<MarkerOptions>)
    }
}