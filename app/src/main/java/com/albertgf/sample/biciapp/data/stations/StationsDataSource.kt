package com.albertgf.sample.biciapp.data.stations

import org.funktionale.either.Either

interface StationsDataSource {
    fun get(key: String): Either<DomainError, StationDomain>
    fun getAll(): Either <DomainError, List<StationMinimalDomain>>
    fun isUpdated(): Boolean = true
    fun populate(stations: List<StationDomain>)
    fun contains(key: String): Boolean = false

}