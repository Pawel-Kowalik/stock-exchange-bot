package com.stockexchangebot.model.mongodb.coincalculatedata

import org.springframework.data.mongodb.core.mapping.Document
import java.math.BigDecimal
import javax.persistence.Id

@Document
data class CoinCalculateData(
        @Id var id: Int,
        var price: BigDecimal,
        var marketType: String,
        var coinType: String
)