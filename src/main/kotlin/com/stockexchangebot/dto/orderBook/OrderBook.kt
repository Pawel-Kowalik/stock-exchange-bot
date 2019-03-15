package com.stockexchangebot.dto.orderBook

data class OrderBook (
    var buy: Collection<Buy>,
    var sell: Collection<Sell>
)