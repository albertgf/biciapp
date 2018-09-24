package com.albertgf.sample.biciapp.domain.stations

import com.albertgf.sample.biciapp.data.stations.StationRepository
import com.albertgf.sample.biciapp.domain.common.DomainError
import org.funktionale.either.Either
import javax.inject.Inject

class GetStationsByAddressUseCase @Inject constructor(private val stationRepository: StationRepository) {

    operator fun invoke(address: String): Either<DomainError, List<StationMinimalDomain>> = stationRepository.findByAddress(address)
}