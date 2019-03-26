package com.stockexchangebot.dto

import java.math.BigDecimal

data class Ticker (
    var Bid: BigDecimal,
    var Ask: BigDecimal,
    var Last: BigDecimal
)