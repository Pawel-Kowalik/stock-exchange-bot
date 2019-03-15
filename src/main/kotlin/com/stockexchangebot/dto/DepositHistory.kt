package com.stockexchangebot.dto

import java.math.BigDecimal
import java.time.LocalDate

data class DepositHistory (
    var id: Int,
    var amount: BigDecimal,
    var currency: String,
    var confirmations: Int,
    var lastUpdated: LocalDate,
    var txId: String,
    var cryptoAddress: String
)