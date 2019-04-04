package com.stockexchangebot.dto.bittrexapi

enum class MarketType(val type: String) {
    USD("USD"),
    BTC("BTC"),
    ETH("ETH"),
    USDT("USDT");

    override fun toString(): String{
        return type
    }


}