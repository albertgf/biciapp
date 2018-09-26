package com.albertgf.sample.biciapp.data.stations

import com.albertgf.sample.apiclient.BiciApiClient
import com.albertgf.sample.apiclient.model.StationApiCLient
import com.albertgf.sample.biciapp.common.optionalRight
import com.albertgf.sample.biciapp.data.BiciDatabase
import com.albertgf.sample.biciapp.data.StationDisk
import com.albertgf.sample.biciapp.data.StationMinimal
import com.albertgf.sample.biciapp.domain.common.DomainError
import com.albertgf.sample.biciapp.domain.common.UnknownDomainError
import com.albertgf.sample.biciapp.domain.stations.StationDomain
import com.albertgf.sample.biciapp.domain.stations.StationMinimalDomain
import org.funktionale.either.Either
import javax.inject.Inject

class ApiStationDataSource @Inject constructor(private val client: BiciApiClient) : StationsDataSource {
    override fun isUpdated(): Boolean {
        // TODO ("not implemented")
        return false
    }

    override fun contains(key: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(key: String): Either<DomainError, StationDomain> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAll(): Either<DomainError, List<StationDomain>> {
        val values = client.getStations()

        val list = values.optionalRight()?.network?.stations ?: ArrayList<StationApiCLient>()

        return Either.right(list.mapIndexed { i, station ->
            convertToDomain(station)
        })
    }

    override fun getAllMinimal(): Either<DomainError, List<StationMinimalDomain>> {

        return Either.left(UnknownDomainError());
    }

    override fun populate(stations: List<StationDomain>) {

    }

    private fun convertToDomain(disk: StationApiCLient) = StationDomain(disk.id, disk.emptySlots, disk.freeBikes, disk.longitude, disk.latitude, disk.name, disk.timestamp, disk.extra.address, disk.extra.districtCode, disk.extra.status, disk.extra.zip, disk.extra.ebikes)
}