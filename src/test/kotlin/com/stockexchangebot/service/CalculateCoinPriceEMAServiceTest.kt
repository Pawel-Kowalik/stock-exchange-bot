package com.stockexchangebot.service

import com.stockexchangebot.SpringBootStockExchangeBotBasedProperties
import com.stockexchangebot.model.mongodb.coincalculatedata.CoinCalculateData
import org.junit.Assert
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired
import java.math.BigDecimal
import java.time.LocalDateTime


class CalculateCoinPriceEMAServiceTest : SpringBootStockExchangeBotBasedProperties() {
    @Autowired lateinit var calculateCoinPriceEMAService: CalculateCoinPriceEMAService
    val coinCalculateData = CoinCalculateData(1, BigDecimal(0.5), "BTC", "LTC",
            LocalDateTime.now())


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

}