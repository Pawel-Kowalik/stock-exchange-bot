package com.stockexchangebot.dto.orderBook

enum class OrderBookType(val type: String) {
    BUY("buy"),
    SELL("sell"),
    BOTH("both")
}