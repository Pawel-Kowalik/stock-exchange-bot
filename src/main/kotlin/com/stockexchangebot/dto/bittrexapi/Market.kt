package com.stockexchangebot.dto.bittrexapi

import java.math.BigDecimal

data class Market (
    var MarketCurrency: String,
    var BaseCurrency: String,
    var MarketCurrencyLong: String,
    var BaseCurrencyLong: String,
    var MinTradeSize: BigDecimal,
    var MarketName: String,
    var IsActive: Boolean,
    var IsRestricted: Boolean
)