package com.stockexchangebot.bittrexapi.dto.orderBook

enum class OrderBookType(val type: String) {
    BUY("buy"),
    SELL("sell"),
    BOTH("both")
}