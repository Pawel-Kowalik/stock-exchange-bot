package com.stockexchangebot.bittrexapi.dto.orderBook

data class OrderBook (
        var buy: Collection<Buy>,
        var sell: Collection<Sell>
)