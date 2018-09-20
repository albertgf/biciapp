package com.albertgf.sample.biciapp.data.stations

import com.albertgf.sample.biciapp.domain.common.DomainError
import com.albertgf.sample.biciapp.domain.stations.StationDomain
import com.albertgf.sample.biciapp.domain.stations.StationMinimalDomain
import org.funktionale.either.Either

interface StationsDataSource {
    fun get(key: String): Either<DomainError, StationDomain>
    fun getAll(): Either <DomainError, List<StationMinimalDomain>>
    fun isUpdated(): Boolean
    fun populate(stations: List<StationDomain>)
    fun contains(key: String): Boolean

}