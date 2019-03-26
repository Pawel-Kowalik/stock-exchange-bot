package com.stockexchangebot.dto

import java.math.BigDecimal
import java.security.Timestamp

data class OrderHistory (
    var OrderUuid: String,
    var Exchange: String,
    var Timestamp: Timestamp,
    var OrderType: String,
    var Limit: BigDecimal,
    var Quantity: BigDecimal,
    var QuantityRemaining: Int,
    var Commision: BigDecimal,
    var Price: BigDecimal,
    var PricePerUnit: BigDecimal,
    var IsConditional: Boolean,
    var ImmediateOrCancel: Boolean
)