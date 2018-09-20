package com.albertgf.sample.biciapp.data.stations

import com.albertgf.sample.biciapp.common.optionalRight
import com.albertgf.sample.biciapp.domain.common.DomainError
import com.albertgf.sample.biciapp.domain.stations.StationMinimalDomain
import org.funktionale.either.Either
import javax.inject.Inject

class StationRepository @Inject constructor(private val dataSourceDisk: DiskStationDataSource, private val dataSourceApi: StationsDataSource) {

    fun Update() {
        if (!dataSourceDisk.isUpdated()) {
            val values = dataSourceApi.getAll()

            values.optionalRight()?.let {
                dataSourceDisk.populate(it)
            }
        }
    }

    fun getAll() : Either<DomainError, List<StationMinimalDomain>> {
        Update()

        return dataSourceDisk.getAllMinimal()
    }
}