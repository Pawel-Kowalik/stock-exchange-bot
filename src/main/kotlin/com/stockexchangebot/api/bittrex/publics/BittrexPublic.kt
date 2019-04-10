package com.stockexchangebot.api.bittrex.publics

import com.stockexchangebot.Utils
import com.stockexchangebot.api.reader.JsonReader
import com.stockexchangebot.bittrexapi.dto.*
import com.stockexchangebot.bittrexapi.dto.orderBook.OrderBook
import com.stockexchangebot.bittrexapi.dto.orderBook.OrderBookType

class BittrexPublic : BittrexPublicDAO {
    var jsonReader: JsonReader = JsonReader()

    private val utils: Utils = Utils()
    private val url = "/public"
    private lateinit var uri: String


    override fun getMarkets() : Collection<*> {
        uri = String.format("%s%s/getmarkets", utils.bittrexApi, url)
        return jsonReader.mapToSuitableDTOS(uri, false, Market::class.java)
    }

    override fun getCurrencies() : Collection<*> {
        uri = String.format("%s%s/getcurrencies", utils.bittrexApi, url)
        return jsonReader.mapToSuitableDTOS(uri, false, Currency::class.java)
    }

    override fun getTicker(marketType: MarketType) : Any? {
        //TODO add coin Type
        uri = String.format("%s%s/getticker?market=%s-%s", utils.bittrexApi, url, marketType.toString())
        return jsonReader.mapToSuitableDTO(uri, false, Ticker::class.java)
    }

    override fun getMarketSummaries() : Collection<*> {
        uri = String.format("%s%s/getmarketsummaries", utils.bittrexApi, url)
        return jsonReader.mapToSuitableDTOS(uri, false, MarketSummary::class.java)
    }

    override fun getMarketSummary(marketType: MarketType) : Collection<*> {
        //TODO add coin Type
        uri = String.format("%s%s/getmarketsummary?market=%s-%s", utils.bittrexApi, url, marketType.toString())
        return jsonReader.mapToSuitableDTOS(uri, false, MarketSummary::class.java)
    }

    override fun getOrderBook(marketType: MarketType, orderBookType: OrderBookType) : Collection<*> {
        //TODO check after implementation is done is OrderBook
        uri = String.format("%s%s/getorderbook?market=%s-%s&type=%s", utils.bittrexApi, url, marketType.toString(), orderBookType.toString())
        return jsonReader.mapToSuitableDTOS(uri, false, OrderBook::class.java)
    }

    override fun getMarketHistory(marketType: MarketType, coinType: String) : Collection<*> {
        uri = String.format("%s%s/getmarkethistory?market=%s-%s", utils.bittrexApi, url, marketType.toString(),
                coinType)
        return jsonReader.mapToSuitableDTOS(uri, false, MarketHistory::class.java)
    }
}