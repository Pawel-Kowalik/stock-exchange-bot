package com.stockexchangebot.api.bittrex.market

import com.stockexchangebot.Utils
import com.stockexchangebot.api.reader.JsonReader
import com.stockexchangebot.dto.*
import java.math.BigDecimal

class BittrexMarket {
    var jsonReader: JsonReader = JsonReader()

    private val utils: Utils = Utils()
    private val url = "/market"
    private lateinit var uri: String

    fun buyLimit(marketType: MarketType, quantity: BigDecimal, rate: BigDecimal) : Any? {
        //TODO add coin type
        uri = String.format("%s%s/buylimit?apikey=%s&market=%s-%s&quantity=%s&rate=%s",
                utils.bittrexApi, url, utils.key, marketType.toString(), quantity, rate)
        return jsonReader.mapToSuitableDTO(uri, true, BuyLimit::class.java)
    }

    fun sellLimit(marketType: MarketType, quantity: BigDecimal, rate: BigDecimal) : Any? {
        //TODO add coin type
        uri = String.format("%s%s/selllimit?apikey=%s&market=%s-%s&quantity=%s&rate=%s",
                utils.bittrexApi, url, utils.key, marketType.toString(), quantity, rate)
        return jsonReader.mapToSuitableDTO(uri, true, SellLimit::class.java)
    }

    fun cancel(orderUuid: String) : Any? {
        uri = String.format("%s%s/cancel?apikey=%s&uuid=%s", utils.bittrexApi, url, utils.key, orderUuid)
        return jsonReader.mapToSuitableDTO(uri, true, Cancel::class.java)
    }

    fun getOpenOrders(marketType: MarketType) : Collection<*> {
        //TODO add coin type
        uri = String.format("%s%s/getopenorders?apikey=%s&market=%s-%s", utils.bittrexApi, url, utils.bittrexApi,
                marketType)
        return jsonReader.mapToSuitableDTOS(uri, true, OpenOrder::class.java)
    }
}