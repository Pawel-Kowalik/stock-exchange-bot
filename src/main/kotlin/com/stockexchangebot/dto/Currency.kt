package com.stockexchangebot.dto

import java.math.BigDecimal

data class Currencies (
        var currency: String,
        var currencyLong: String,
        var minConfirmation: Int,
        var txFee: BigDecimal,
        var isActive: Boolean,
        var isRestricted: Boolean,
        var coinType: String,
        var baseAddress: Any
)