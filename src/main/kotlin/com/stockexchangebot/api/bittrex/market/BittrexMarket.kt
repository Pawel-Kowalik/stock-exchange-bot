package com.stockexchangebot.api.bittrex.market

import com.stockexchangebot.Utils
import com.stockexchangebot.api.reader.JsonReader
import com.stockexchangebot.dto.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal

class BittrexMarket {
    @Autowired lateinit var jsonReader: JsonReader

    private val utils: Utils = Utils()
    private val url = "/market"
    private lateinit var uri: String

    fun buyLimit(marketType: MarketType, quantity: BigDecimal, rate: BigDecimal) : Any? {
        //TODO add coin type
        uri = String.format("%s%s/buylimit?apikey=%s&market=%s-%s&quantity=%s&rate=%s",
                utils.bittrexApi, url, utils.key, marketType.toString(), quantity, rate)
        return jsonReader.mapToSuitableDTO(uri, BuyLimit::class.java)
    }

    fun sellLimit(marketType: MarketType, quantity: BigDecimal, rate: BigDecimal) : Any? {
        //TODO add coin type
        uri = String.format("%s%s/selllimit?apikey=%s&market=%s-%s&quantity=%s&rate=%s",
                utils.bittrexApi, url, utils.key, marketType.toString(), quantity, rate)
        return jsonReader.mapToSuitableDTO(uri, SellLimit::class.java)
    }

    fun cancel(orderUuid: String) : Any? {
        uri = String.format("%s%s/cancel?apikey=%s&uuid=%s", utils.bittrexApi, url, utils.key, orderUuid)
        return jsonReader.mapToSuitableDTO(uri, Cancel::class.java)
    }

    fun getOpenOrders(marketType: MarketType) : Collection<*> {
        uri = String.format("%s%s/getopenorders?apikey=%s&market=%s-%s", utils.bittrexApi, url, utils.bittrexApi,
                marketType)
        return jsonReader.mapToSuitableDTOS(uri, OpenOrder::class.java)
    }
}