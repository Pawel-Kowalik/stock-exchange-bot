package com.stockexchangebot.api.bittrex.publics

import com.stockexchangebot.dto.bittrexapi.MarketType
import com.stockexchangebot.dto.bittrexapi.orderBook.OrderBookType


interface BittrexPublicDAO {
    fun getMarkets() : Collection<*>

    fun getCurrencies() : Collection<*>

    fun getTicker(marketType: MarketType) : Any?

    fun getMarketSummaries() : Collection<*>

    fun getMarketSummary(marketType: MarketType) : Collection<*>

    fun getOrderBook(marketType: MarketType, orderBookType: OrderBookType) : Collection<*>

    fun getMarketHistory(marketType: MarketType) : Collection<*>
}