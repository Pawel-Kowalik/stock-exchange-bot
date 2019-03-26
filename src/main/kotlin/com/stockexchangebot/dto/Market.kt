package com.stockexchangebot.dto

import java.math.BigDecimal
import java.time.LocalDate

data class Markets (
    var marketCurrency: String,
    var baseCurrency: String,
    var marketCurrencyLong: String,
    var baseCurrencyLong: String,
    var minTradeSize: BigDecimal,
    var marketName: String,
    var isActive: Boolean,
    var isRestricted: Boolean,
    var created: LocalDate
)