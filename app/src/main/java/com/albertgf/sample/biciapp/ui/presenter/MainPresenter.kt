package com.albertgf.sample.biciapp.ui.presenter

import android.content.res.Resources
import android.graphics.*
import com.albertgf.sample.biciapp.domain.common.DomainError
import com.albertgf.sample.biciapp.domain.stations.GetStationsUseCase
import com.albertgf.sample.biciapp.domain.stations.StationMinimalDomain
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import org.funktionale.either.Either
import java.lang.Exception
import javax.inject.Inject
import android.graphics.Paint.ANTI_ALIAS_FLAG
import com.albertgf.sample.biciapp.R
import com.albertgf.sample.biciapp.domain.common.UnknownDomainError


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


        val resources : Resources = view?.getResources() ?: return Either.left(UnknownDomainError())

        return when {
            result.isRight() -> Either.right(result.right().get().mapIndexed {
                index, station ->
                MarkerOptions().position(LatLng(station.latitude, station.longitude)).title(station.name).icon(BitmapDescriptorFactory.fromBitmap(generateBitmap(station.freeBikes, station.emptySlots, resources)))
            })
            else -> Either.left(result.left().get())
        }
    }

    private fun generateBitmap(free: Int, slots: Int, resources: Resources) : Bitmap {
        val scale = resources.getDisplayMetrics().density
        var bitmap = BitmapFactory.decodeResource(resources, R.drawable.marker)
        bitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true)

        val freeText: String = free.toString()
        val slotsText: String = slots.toString()
        val canvas = Canvas(bitmap)
        val paint = Paint(ANTI_ALIAS_FLAG)
        paint.setColor(Color.WHITE) // Text color
        paint.setTextSize(16 * scale) // Text size
        paint.setShadowLayer(1f, 0f, 1f, Color.WHITE) // Text shadow
        val boundsFree = Rect()
        paint.getTextBounds(freeText, 0, freeText.length, boundsFree)
        val boundsSlots = Rect()
        paint.getTextBounds(slotsText, 0, slotsText.length, boundsSlots)
        val xFree : Float = ((bitmap.width - boundsFree.width()-10) / 2f)
        val xSlots : Float = ((bitmap.width - boundsSlots.width()-10) / 2f)
        val yFree : Float = bitmap.height /3.5f
        val ySlots : Float = bitmap.height /1.6f
        canvas.drawText(freeText, xFree, yFree, paint)
        canvas.drawText(slotsText, xSlots, ySlots, paint)

        return bitmap
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

        fun getResources() : Resources
    }
}