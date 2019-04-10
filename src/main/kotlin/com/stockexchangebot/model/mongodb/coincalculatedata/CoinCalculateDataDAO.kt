package com.stockexchangebot.model.mongodb.coincalculatedata

import org.springframework.data.mongodb.repository.MongoRepository
import java.time.LocalDate

interface CoinCalculateDataDAO : MongoRepository<CoinCalculateData, Int> {
    fun getByCoinTypeOrderByIdAsc(coinType: String): Collection<CoinCalculateData>

    fun getByDateBetween(today: LocalDate, deltaDay: LocalDate): Collection<CoinCalculateData>
}