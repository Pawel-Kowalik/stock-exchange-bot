package com.stockexchangebot.dto

import java.math.BigDecimal
import java.time.LocalDate

data class DepositHistory (
    var Id: Int,
    var Amount: BigDecimal,
    var Currency: String,
    var Confirmations: Int,
    var LastUpdated: LocalDate,
    var TxId: String,
    var CryptoAddress: String
)