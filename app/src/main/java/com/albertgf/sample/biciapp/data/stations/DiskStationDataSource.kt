package com.albertgf.sample.biciapp.data.stations

import com.albertgf.sample.biciapp.data.BiciDatabase
import com.albertgf.sample.biciapp.data.StationDisk
import com.albertgf.sample.biciapp.data.StationMinimal
import com.albertgf.sample.biciapp.domain.common.DomainError
import com.albertgf.sample.biciapp.domain.stations.StationDomain
import com.albertgf.sample.biciapp.domain.stations.StationMinimalDomain
import org.funktionale.either.Either
import javax.inject.Inject

class DiskStationDataSource @Inject constructor(private val db: BiciDatabase) : StationsDataSource {
    override fun isUpdated(): Boolean {
        // TODO ("not implemented")
        return true
    }

    override fun contains(key: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(key: String): Either<DomainError, StationDomain> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getAll(): Either<DomainError, List<StationMinimalDomain>> {

        val values = db.stationsDataDao().getStationsMinimal()

        return Either.right(values.mapIndexed { i, stationMinimal ->
            convetToDomain(stationMinimal)
        })
    }

    override fun populate(stations: List<StationDomain>) {
        val disk = stations.mapIndexed { i , stationMinimal ->
            convertFromDomain(stationMinimal)
        }
        db.stationsDataDao().updateData(disk)
    }

    private fun convertFromDomain(domain: StationDomain) = StationDisk(domain.id, domain.emptySlots, domain.freeBikes, domain.longitude, domain.latitude, domain.name, domain.timestamp, domain.address, domain.districtCode, domain.status, domain.zip, domain.ebikes)

    private fun convetToDomain(disk: StationDisk) = StationDomain(disk.id, disk.emptySlots, disk.freeBikes, disk.longitude, disk.latitude, disk.name, disk.timestamp, disk.address, disk.districtCode, disk.status, disk.zip, disk.ebikes)

    private fun convetToDomain(disk: StationMinimal) = StationMinimalDomain(disk.id, disk.emptySlots, disk.freeBikes, disk.longitude, disk.latitude,  disk.status, disk.ebikes)
}