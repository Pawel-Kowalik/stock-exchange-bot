package com.stockexchangebot.dto

import java.math.BigDecimal

data class Ticker (
    var bid: BigDecimal,
    var ask: BigDecimal,
    var last: BigDecimal
)