package com.stockexchangebot.service

import com.stockexchangebot.api.bittrex.publics.BittrexPublicFacade
import com.stockexchangebot.bittrexapi.dto.MarketHistory
import com.stockexchangebot.bittrexapi.dto.MarketType
import com.stockexchangebot.model.mongodb.coincalculatedata.CoinCalculateData
import com.stockexchangebot.model.mongodb.coincalculatedata.CoinCalculateDataDAO
import com.stockexchangebot.model.postgres.CoinType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.*
import java.util.concurrent.ForkJoinPool
import java.util.stream.Collectors
import kotlin.math.pow


@Service
class CalculateCoinPriceEMAService @Autowired constructor(
        private var coinCalculateDataDAO: CoinCalculateDataDAO,
        private var bittrexPublicFacade: BittrexPublicFacade) {


    fun calculateEMA(index: Int, coinCalculateData: CoinCalculateData, alpha: Double): Double {
        var emaValue = 0.0

        if(index == 0 && coinCalculateData.price != BigDecimal.ZERO) {
            emaValue = (coinCalculateData.price.toDouble().div(1.toDouble()))
        }
        else if((1 - alpha) != 0.0)
            emaValue = ((((1 - alpha).pow(index)).times(coinCalculateData.price.toDouble())).div(1.toDouble().minus(alpha)))

        return emaValue
    }

    fun saveCoinCalculateDataToMongo(coinTypes: List<CoinType>) {
        val forkJoinPool = ForkJoinPool(24)

        forkJoinPool.submit {
            coinTypes.parallelStream().forEach { coinType ->
                try {
                    val coinCalculateData = mapMarketHistories(coinType.coinName, MarketType.BTC)
                    if(coinCalculateData.isNotEmpty())
                        coinCalculateDataDAO.saveAll(coinCalculateData)
                } catch (e: Exception) {
                    e.stackTrace
                }
            }
        }.get()
    }

    private fun mapMarketHistories(coinType: String, marketType: MarketType) : List<CoinCalculateData> {
        val marketHistory = bittrexPublicFacade.getMarketHistory(marketType, coinType)
                as List<MarketHistory>

        return marketHistory.stream()
                .sorted(Comparator.comparingInt { it.Id })
                .limit(26)
                .map { mapToCoinCalculateData(it, coinType, marketType) }
                .collect(Collectors.toList())

    }

    private fun mapToCoinCalculateData(marketHistory: MarketHistory, coinType: String,
                                       marketType: MarketType) : CoinCalculateData {
        return CoinCalculateData(
                marketHistory.Id,
                marketHistory.Price,
                marketType.toString(),
                coinType,
                LocalDateTime.now())
    }
}