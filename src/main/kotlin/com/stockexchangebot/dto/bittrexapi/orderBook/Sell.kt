package com.stockexchangebot.dto.bittrexapi.orderBook

import java.math.BigDecimal

data class Sell (
    var quantity: BigDecimal,
    var rate: BigDecimal
)