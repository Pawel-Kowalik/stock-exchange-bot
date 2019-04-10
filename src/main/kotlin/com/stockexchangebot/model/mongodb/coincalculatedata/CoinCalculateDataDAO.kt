package com.stockexchangebot.model.mongodb.coincalculatedata

import org.springframework.data.mongodb.repository.MongoRepository

interface CoinCalculateDataDAO : MongoRepository<CoinCalculateData, Int> {
    fun getByCoinTypeOrderByIdAsc(coinType: String): Collection<CoinCalculateData>
}