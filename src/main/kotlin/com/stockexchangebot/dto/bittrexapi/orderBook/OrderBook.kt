package com.stockexchangebot.dto.bittrexapi.orderBook

data class OrderBook (
        var buy: Collection<Buy>,
        var sell: Collection<Sell>
)