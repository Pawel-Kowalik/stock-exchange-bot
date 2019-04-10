package com.stockexchangebot.service

import com.stockexchangebot.api.bittrex.publics.BittrexPublicFacade
import com.stockexchangebot.bittrexapi.dto.MarketHistory
import com.stockexchangebot.bittrexapi.dto.MarketType
import com.stockexchangebot.model.mongodb.coincalculatedata.CoinCalculateData
import com.stockexchangebot.model.mongodb.coincalculatedata.CoinCalculateDataDAO
import com.stockexchangebot.model.mongodb.coinsellema.CoinEMA
import com.stockexchangebot.model.mongodb.coinsellema.CoinEMADAO
import com.stockexchangebot.model.postgres.CoinType
import com.stockexchangebot.model.postgres.CoinTypeDAO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.concurrent.ForkJoinPool
import java.util.stream.Collectors
import kotlin.math.pow


@Service
class CalculateCoinPriceEMAService @Autowired constructor(
        private var coinTypeDAO: CoinTypeDAO,
        private var coinCalculateDataDAO: CoinCalculateDataDAO,
        private var coinEMADAO: CoinEMADAO,
        private var bittrexPublicFacade: BittrexPublicFacade) {

    fun mapMarketHistoryToCoinCalculateDate() {
        val coinTypes = getCoinTypes()

        saveCoinCalculateDataToMongo(coinTypes)
        saveCoinsAverageEMAToMongo(coinTypes)
    }

    fun saveCoinsAverageEMAToMongo(coinTypes: List<CoinType>) {
        val coinsEMA = calculateCoinsEMA(coinTypes)

        coinEMADAO.saveAll(coinsEMA)
    }

    fun calculateCoinsEMA(coinTypes: List<CoinType>) : List<CoinEMA> {
        var emaValue = 0.0
        val coinsEma: MutableList<CoinEMA> = ArrayList()

        coinTypes.stream().forEach{ coinType ->
            val listSize = coinCalculateDataDAO.getByCoinTypeOrderByIdAsc(coinType.coinName).size
            if(listSize != 0) {
                val alpha: Double = (2.toDouble().div((listSize + 1)))

                coinCalculateDataDAO.getByCoinTypeOrderByIdAsc(coinType.coinName)
                        .forEachIndexed { index, coinCalculateData ->
                            emaValue += calculateEMA(index, coinCalculateData, alpha)
                        }
                val coinEMA = CoinEMA(LocalDateTime.now(), coinType.coinName, emaValue)
                coinsEma += coinEMA
                emaValue = 0.0
            }
        }
        return coinsEma
    }

    fun calculateEMA(index: Int, coinCalculateData: CoinCalculateData, alpha: Double): Double {
        var emaValue = 0.0

        if(index == 0 && coinCalculateData.price != BigDecimal.ZERO) {
            emaValue = (coinCalculateData.price.toDouble().div(1.toDouble()))
        }
        else if((1 - alpha) != 0.0)
            emaValue = ((((1 - alpha).pow(index)).times(coinCalculateData.price.toDouble())).div(1.toDouble().minus(alpha)))

        return emaValue
    }

    private fun saveCoinCalculateDataToMongo(coinTypes: List<CoinType>) {
        val forkJoinPool = ForkJoinPool(24)

        forkJoinPool.submit {
            coinTypes.parallelStream().forEach { coinType ->
                try {
                    coinCalculateDataDAO.saveAll(mapMarketHistories(coinType.coinName, MarketType.BTC))
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
                .map { mapToCoinCalculateData(it, coinType, marketType) }
                .collect(Collectors.toList())

    }

    private fun mapToCoinCalculateData(marketHistory: MarketHistory, coinType: String,
                                       marketType: MarketType) : CoinCalculateData {
        return CoinCalculateData(
                marketHistory.Id,
                marketHistory.Price,
                marketType.toString(),
                coinType)
    }

    private fun getCoinTypes(): List<CoinType> {
        return coinTypeDAO.findAll().toList()
    }
}