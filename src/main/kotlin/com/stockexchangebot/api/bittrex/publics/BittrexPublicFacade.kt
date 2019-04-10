package com.stockexchangebot.api.bittrex.publics

import com.stockexchangebot.bittrexapi.dto.MarketType
import com.stockexchangebot.bittrexapi.dto.orderBook.OrderBookType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class BittrexPublicFacade @Autowired constructor(private val bittrexPublicDAO: BittrexPublicDAO) {

    fun getMarkets() : Collection<*> {
        return bittrexPublicDAO.getMarkets()
    }

    fun getCurrencies() : Collection<*> {
        return bittrexPublicDAO.getCurrencies()
    }

    fun getTicker(marketType: MarketType) : Any? {
        return bittrexPublicDAO.getTicker(marketType)
    }

    fun getMarketSummaries() : Collection<*> {
        return bittrexPublicDAO.getMarketSummaries()
    }

    fun getMarketSummary(marketType: MarketType) : Collection<*> {
        return bittrexPublicDAO.getMarketSummary(marketType)
    }

    fun getOrderBook(marketType: MarketType, orderBookType: OrderBookType) : Collection<*> {
        return bittrexPublicDAO.getOrderBook(marketType, orderBookType)
    }

    fun getMarketHistory(marketType: MarketType, coinType: String) : Collection<*> {
        return bittrexPublicDAO.getMarketHistory(marketType, coinType)
    }
}