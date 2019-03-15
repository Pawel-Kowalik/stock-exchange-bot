package com.stockexchangebot.dto

import java.math.BigDecimal
import java.security.Timestamp

data class OrderHistory (
    var orderUuid: String,
    var exchange: String,
    var timestamp: Timestamp,
    var orderType: String,
    var limit: BigDecimal,
    var quantity: BigDecimal,
    var quantityRemaining: Int,
    var commision: BigDecimal,
    var price: BigDecimal,
    var pricePerUnit: BigDecimal,
    var isConditional: Boolean,
    var immediateOrCancel: Boolean
)