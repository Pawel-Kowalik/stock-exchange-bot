package com.stockexchangebot.bittrexapi.dto.orderBook

import java.math.BigDecimal

data class Sell (
    var quantity: BigDecimal,
    var rate: BigDecimal
)