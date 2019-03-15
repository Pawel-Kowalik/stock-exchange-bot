package com.stockexchangebot.dto

import java.math.BigDecimal
import java.time.LocalDate

data class WithdrawalHistory (
    var paymentUuid: String,
    var amount: Int,
    var address: String,
    var opened: LocalDate,
    var authorized: Boolean,
    var pendingPayment: Boolean,
    var txCost: BigDecimal,
    var txId: String,
    var canceled: Boolean,
    var invalidAddress: Boolean
)