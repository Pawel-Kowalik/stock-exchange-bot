package com.stockexchangebot.dto

import java.math.BigDecimal
import java.security.Timestamp

data class MarketHistory (
    var id: Int,
    var timestamp: Timestamp,
    var quantity: BigDecimal,
    var price: BigDecimal,
    var total: BigDecimal,
    var fillType: String,
    var orderType: String
)