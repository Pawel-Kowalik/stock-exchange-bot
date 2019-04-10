package com.stockexchangebot.bittrexapi.dto

import java.math.BigDecimal

data class Currency (
        var Currency: String,
        var CurrencyLong: String,
        var MinConfirmation: Int,
        var TxFee: BigDecimal,
        var IsActive: Boolean,
        var IsRestricted: Boolean,
        var CoinType: String,
        var BaseAddress: Any
)