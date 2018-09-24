package com.albertgf.sample.biciapp.ui.presenter

import com.albertgf.sample.biciapp.domain.common.DomainError
import com.albertgf.sample.biciapp.domain.stations.GetStationsUseCase
import com.albertgf.sample.biciapp.domain.stations.StationMinimalDomain
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
                val result = async(CommonPool) { getStationsUseCase()}.await()

                when (result) {
                    is Either.Right -> showStations(result.r)
                    is Either.Left -> view?.showDomainError(result.l)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun showStations(result: List<StationMinimalDomain>) {
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
        fun showStations(list: List<StationMinimalDomain>)
    }
}