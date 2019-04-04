package com.stockexchangebot.dto.bittrexapi.orderBook

enum class OrderBookType(val type: String) {
    BUY("buy"),
    SELL("sell"),
    BOTH("both")
}