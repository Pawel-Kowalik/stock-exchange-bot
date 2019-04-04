package com.stockexchangebot.dto.bittrexapi

import java.math.BigDecimal

data class MarketSummary (
    var MarketName: String,
    var High: BigDecimal,
    var Low: BigDecimal,
    var Volume: BigDecimal,
    var Last: BigDecimal,
    var BaseVolume: BigDecimal,
    var TimeStamp: Any,
    var Bid: BigDecimal,
    var Ask: BigDecimal,
    var OpenBuyOrders: Int,
    var OpenSellOrders: Int,
    var PrevDay: BigDecimal
)