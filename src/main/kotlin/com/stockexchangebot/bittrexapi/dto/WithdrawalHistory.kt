package com.stockexchangebot.bittrexapi.dto

import java.math.BigDecimal
import java.time.LocalDate

data class WithdrawalHistory (
    var PaymentUuid: String,
    var Amount: Int,
    var Address: String,
    var Opened: LocalDate,
    var Authorized: Boolean,
    var PendingPayment: Boolean,
    var TxCost: BigDecimal,
    var TxId: String,
    var Canceled: Boolean,
    var InvalidAddress: Boolean
)