package com.stockexchangebot.service

import com.stockexchangebot.model.mongodb.coincalculatedata.CoinCalculateData
import com.stockexchangebot.model.mongodb.coincalculatedata.CoinCalculateDataDAO
import com.stockexchangebot.model.mongodb.macd.CoinMACD
import com.stockexchangebot.model.mongodb.macd.CoinMACDDAO
import com.stockexchangebot.model.mongodb.signalmacd.CoinSignalMACD
import com.stockexchangebot.model.mongodb.signalmacd.SignalMACDDAO
import com.stockexchangebot.model.postgres.CoinType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class CalculateMACDService @Autowired constructor(
        private var coinCalculateDataDAO: CoinCalculateDataDAO,
        private var coinMACDDAO: CoinMACDDAO,
        private var signalMACDDAO: SignalMACDDAO,
        private var calculateCoinPriceEMAService: CalculateCoinPriceEMAService) {

    fun calculateCoinsEMA(coinTypes: List<CoinType>) {
        val coinsMACD: MutableList<CoinMACD> = ArrayList()
        val signalMACD: MutableList<CoinSignalMACD> = ArrayList()

        coinTypes.stream().forEach{ coinType ->
            var coinsCalculateData = coinCalculateDataDAO.getFirst26ByCoinTypeOrderByIdDesc(coinType.coinName)

            val coins26TransactionsEMA = calculateCoinsTransactionsEMA(26, coinsCalculateData)

                coinsCalculateData = coinsCalculateData.take(12)
                val coins12TransactionsEMA = calculateCoinsTransactionsEMA(12, coinsCalculateData)

                coinsCalculateData = coinsCalculateData.take(9)
                val coinSignalMACD = calculateCoinsTransactionsEMA(9, coinsCalculateData)

                signalMACD.add(CoinSignalMACD(LocalDateTime.now(), coinType.coinName, coinSignalMACD))

                val coinMACD = coins26TransactionsEMA.minus(coins12TransactionsEMA)
                coinsMACD.add(CoinMACD(LocalDateTime.now(), coinType.coinName, coinMACD))
        }
        signalMACDDAO.saveAll(signalMACD)
        coinMACDDAO.saveAll(coinsMACD)

    }

    private fun calculateCoinsTransactionsEMA(transactionNumber: Int, coinsCalculateData: Collection<CoinCalculateData>): Double {
        val alpha = calculateEMAAlpha(transactionNumber)
        return calculateCoinsEMA(coinsCalculateData, alpha)
    }

    private fun calculateCoinsEMA(coinsCalculateData: Collection<CoinCalculateData>, alpha: Double) : Double {
        var emaValue = 0.0
        coinsCalculateData.forEachIndexed { index, coinCalculateData ->
            emaValue += calculateCoinPriceEMAService.calculateEMA(index, coinCalculateData, alpha)
        }

        return emaValue
    }

    private fun calculateEMAAlpha(numberOfTransactions : Int): Double {
        return (2.toDouble().div((numberOfTransactions + 1)))
    }
}