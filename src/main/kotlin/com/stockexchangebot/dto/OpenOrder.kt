package com.stockexchangebot.dto

import java.math.BigDecimal
import java.time.LocalDate

data class OpenOrder (
    var uuid: String,
    var orderUuid: String,
    var exchange: String,
    var orderType: String,
    var quantity: Int,
    var quantityReaming: Int,
    var limit: BigDecimal,
    var commissionPaid: Int,
    var price: BigDecimal,
    var pricePerUnit: BigDecimal,
    var opened: LocalDate,
    var closed: Any,
    var cancelInitiated: Boolean,
    var immediateOrCancel: Boolean,
    var isConditional: Boolean
)