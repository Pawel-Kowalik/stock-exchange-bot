package com.stockexchangebot.dto.bittrexapi

import java.math.BigDecimal

data class Balance (
    var Currency: String,
    var Balance: BigDecimal,
    var Available: BigDecimal,
    var Pending: Int,
    var CryptoAddress: String,
    var Requested: Boolean,
    var Uuid: Any
)