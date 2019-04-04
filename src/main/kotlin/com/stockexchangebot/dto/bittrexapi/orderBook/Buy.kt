package com.stockexchangebot.dto.bittrexapi.orderBook

import java.math.BigDecimal

data class Buy (
    var quantity: BigDecimal,
    var rate: BigDecimal
)