package com.stockexchangebot.dto

import java.math.BigDecimal
import java.security.Timestamp
import java.time.LocalDate

data class MarketSummary (
    var marketName: String,
    var high: BigDecimal,
    var low: BigDecimal,
    var volume: BigDecimal,
    var last: BigDecimal,
    var baseVolume: BigDecimal,
    var timeStamp: Timestamp,
    var bid: BigDecimal,
    var ask: BigDecimal,
    var openBuyOrders: Int,
    var openSellOrders: Int,
    var prevDay: BigDecimal,
    var created: LocalDate,
    var displayMarketName: String
)