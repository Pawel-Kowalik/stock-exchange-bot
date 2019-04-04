package com.stockexchangebot.dto.bittrexapi

import java.math.BigDecimal

data class MarketHistory (
    var Id: Int,
    var TimeStamp: Any,
    var Quantity: BigDecimal,
    var Price: BigDecimal,
    var Total: BigDecimal,
    var FillType: String,
    var OrderType: String
)