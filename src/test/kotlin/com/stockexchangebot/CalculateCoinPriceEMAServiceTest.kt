package com.stockexchangebot

import com.stockexchangebot.model.mongodb.coincalculatedata.CoinCalculateData
import com.stockexchangebot.model.postgres.CoinType
import com.stockexchangebot.service.CalculateCoinPriceEMAService
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner
import java.math.BigDecimal
import java.time.LocalDate

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CalculateCoinPriceEMAServiceTest {
    @Autowired lateinit var calculateCoinPriceEMAService: CalculateCoinPriceEMAService
    val coinCalculateData = CoinCalculateData(1, BigDecimal(0.5), "BTC", "LTC", LocalDate.now())


    @Test
    fun coinPriceEMAServiceForFirstIterateTest() {
        val priceEMA = calculateCoinPriceEMAService.calculateEMA(0, coinCalculateData, 0.45)

        Assert.assertEquals(0.5, priceEMA, 0.0)
    }

    @Test
    fun coinPriceEMAServiceForPowOneTest() {
        val priceEMA = calculateCoinPriceEMAService.calculateEMA(1, coinCalculateData, 0.56)

        Assert.assertEquals(0.5, priceEMA, 0.0)
    }

    @Test
    fun coinPriceEMAServiceForPowFiveTest() {
        val priceEMA = calculateCoinPriceEMAService.calculateEMA(5, coinCalculateData, 0.654)

        Assert.assertEquals(0.007165960327999998, priceEMA, 0.0)
    }

    @Test
    fun calculateCoinsEMAForOneCoinTypeTest() {
        val coinsType: MutableList<CoinType> = ArrayList()
        val coinCalculateData: MutableList<CoinCalculateData> = ArrayList()

        coinsType += CoinType("LTC", 1)

        coinCalculateData += CoinCalculateData(1, BigDecimal(0.45),  "BTC", "LTC")
        coinCalculateData += CoinCalculateData(1, BigDecimal(0.4589),  "BTC", "LTC")
        coinCalculateData += CoinCalculateData(1, BigDecimal(0.876),  "BTC", "LTC")
        coinCalculateData += CoinCalculateData(1, BigDecimal(0.134), "BTC", "LTC")
        coinCalculateData += CoinCalculateData(1, BigDecimal(0.0098776),  "BTC", "LTC")

        val tmp = calculateCoinPriceEMAService.calculateCoinsEMA(coinsType)

        Assert.assertEquals(1.555382251851852, tmp.get(0).EMA, 0.0)
    }

//    @Test
//    fun calculateCoinsEMAForManyCoinTypeTest() {
//        val coinsType: MutableList<CoinType> = ArrayList()
//        val coinCalculateData: MutableList<CoinCalculateData> = ArrayList()
//
//        coinsType += CoinType("LTC", 1)
//        coinsType += CoinType("ADA", 1)
//        coinsType += CoinType("ETH", 1)
//        coinsType += CoinType("OMG", 1)
//        coinsType += CoinType("XD", 1)
//
//        coinCalculateData += CoinCalculateData(1, BigDecimal(0.45), "SELL", "BTC", "LTC")
//        coinCalculateData += CoinCalculateData(1, BigDecimal(0.4589), "SELL", "BTC", "LTC")
//        coinCalculateData += CoinCalculateData(1, BigDecimal(0.876), "SELL", "BTC", "LTC")
//        coinCalculateData += CoinCalculateData(1, BigDecimal(0.134), "SELL", "BTC", "LTC")
//        coinCalculateData += CoinCalculateData(1, BigDecimal(0.0098776), "SELL", "BTC", "LTC")
//
//        coinCalculateData += CoinCalculateData(1, BigDecimal(0.45), "SELL", "BTC", "ADA")
//        coinCalculateData += CoinCalculateData(1, BigDecimal(0.4589), "SELL", "BTC", "ADA")
//        coinCalculateData += CoinCalculateData(1, BigDecimal(0.876), "SELL", "BTC", "ADA")
//        coinCalculateData += CoinCalculateData(1, BigDecimal(0.134), "SELL", "BTC", "ADA")
//        coinCalculateData += CoinCalculateData(1, BigDecimal(0.0098776), "SELL", "BTC", "ADA")
//
//        coinCalculateData += CoinCalculateData(1, BigDecimal(0.45), "SELL", "BTC", "ETH")
//        coinCalculateData += CoinCalculateData(1, BigDecimal(0.4589), "SELL", "BTC", "ETH")
//        coinCalculateData += CoinCalculateData(1, BigDecimal(0.876), "SELL", "BTC", "ETH")
//        coinCalculateData += CoinCalculateData(1, BigDecimal(0.134), "SELL", "BTC", "ETH")
//        coinCalculateData += CoinCalculateData(1, BigDecimal(0.0098776), "SELL", "BTC", "ETH")
//
//        coinCalculateData += CoinCalculateData(1, BigDecimal(0.45), "SELL", "BTC", "OMG")
//        coinCalculateData += CoinCalculateData(1, BigDecimal(0.4589), "SELL", "BTC", "OMG")
//        coinCalculateData += CoinCalculateData(1, BigDecimal(0.876), "SELL", "BTC", "OMG")
//        coinCalculateData += CoinCalculateData(1, BigDecimal(0.134), "SELL", "BTC", "OMG")
//        coinCalculateData += CoinCalculateData(1, BigDecimal(0.0098776), "SELL", "BTC", "OMG")
//
//        coinCalculateData += CoinCalculateData(1, BigDecimal(0.45), "SELL", "BTC", "XD")
//        coinCalculateData += CoinCalculateData(1, BigDecimal(0.4589), "SELL", "BTC", "XD")
//        coinCalculateData += CoinCalculateData(1, BigDecimal(0.876), "SELL", "BTC", "XD")
//        coinCalculateData += CoinCalculateData(1, BigDecimal(0.134), "SELL", "BTC", "XD")
//        coinCalculateData += CoinCalculateData(1, BigDecimal(0.0098776), "SELL", "BTC", "XD")
//
//        val tmp = calculateCoinPriceEMAService.calculateCoinsEMA(coinsType,"SELL", coinCalculateData)
//
//        Assert.assertEquals(1.555382251851852, tmp.get(0).EMA, 0.0)
//        Assert.assertEquals(1.555382251851852, tmp.get(1).EMA, 0.0)
//        Assert.assertEquals(1.555382251851852, tmp.get(2).EMA, 0.0)
//        Assert.assertEquals(1.555382251851852, tmp.get(3).EMA, 0.0)
//        Assert.assertEquals(1.555382251851852, tmp.get(4).EMA, 0.0)
//    }
}