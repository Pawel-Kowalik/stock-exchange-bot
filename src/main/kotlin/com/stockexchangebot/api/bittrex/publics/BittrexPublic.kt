package com.stockexchangebot.api.bittrex.publics

import com.stockexchangebot.Utils
import com.stockexchangebot.api.reader.JsonReader
import com.stockexchangebot.dto.*
import com.stockexchangebot.dto.orderBook.OrderBook
import com.stockexchangebot.dto.orderBook.OrderBookType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping

class BittrexPublic {
    @Autowired lateinit var jsonReader: JsonReader

    private val utils: Utils = Utils()
    private val url = "/public"
    private lateinit var uri: String


    fun getMarkets() : Collection<*> {
        uri = String.format("%s%s/getmarkets", utils.bittrexApi, url)
        return jsonReader.mapToSuitableDTOS(uri, Market::class.java)
    }

    fun getCurrencies() : Collection<*> {
        uri = String.format("%s%s/getcurrencies", utils.bittrexApi, url)
        return jsonReader.mapToSuitableDTOS(uri, Currency::class.java)
    }

    fun getTicker(marketType: MarketType) : Any? {
        //TODO add coin Type
        uri = String.format("%s%s/getticker?market=%s-%s", utils.bittrexApi, url, marketType.toString())
        return jsonReader.mapToSuitableDTO(uri, Ticker::class.java)
    }

    fun getMarketSummaries() : Collection<*> {
        uri = String.format("%s%s/getmarketsummaries", utils.bittrexApi, url)
        return jsonReader.mapToSuitableDTOS(uri, MarketSummary::class.java)
    }

    fun getMarketSummary(marketType: MarketType) : Collection<*> {
        //TODO add coin Type
        uri = String.format("%s%s/getmarketsummary?market=%s-%s", utils.bittrexApi, url, marketType.toString())
        return jsonReader.mapToSuitableDTOS(uri, MarketSummary::class.java)
    }

    fun getOrderBook(marketType: MarketType, orderBookType: OrderBookType) : Collection<*> {
        //TODO check after implementation is done is OrderBook
        uri = String.format("%s%s/getorderbook?market=%s-%s&type=%s", utils.bittrexApi, url, marketType.toString(), orderBookType.toString())
        return jsonReader.mapToSuitableDTOS(uri, OrderBook::class.java)
    }

    @GetMapping("/getmarkethistory?market={ }")
    fun getMarketHistory(marketType: MarketType) : Collection<*> {
        uri = String.format("%s%s/getmarkethistory?market=%s", utils.bittrexApi, url, marketType.toString())
        return jsonReader.mapToSuitableDTOS(uri, MarketHistory::class.java)
    }
}