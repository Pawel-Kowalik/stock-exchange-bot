package com.stockexchangebot.dto

import java.math.BigDecimal

data class Balance (
    var currency: String,
    var balance: BigDecimal,
    var available: BigDecimal,
    var pending: Int,
    var cryptoAddress: String,
    var requested: Boolean,
    var uuid: Any
)