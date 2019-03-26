package com.stockexchangebot.dto

import java.math.BigDecimal
import java.time.LocalDate

data class OpenOrder (
    var Uuid: String,
    var OrderUuid: String,
    var Exchange: String,
    var OrderType: String,
    var Quantity: Int,
    var QuantityReaming: Int,
    var Limit: BigDecimal,
    var CommissionPaid: Int,
    var Price: BigDecimal,
    var PricePerUnit: BigDecimal,
    var Opened: LocalDate,
    var Closed: Any,
    var CancelInitiated: Boolean,
    var ImmediateOrCancel: Boolean,
    var IsConditional: Boolean
)