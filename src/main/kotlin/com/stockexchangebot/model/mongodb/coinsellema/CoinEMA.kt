package com.stockexchangebot.model.mongodb.coinsellema

import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import javax.persistence.Id

@Document
data class CoinEMA (
        @Id var dateTime: LocalDateTime,
        var coinType: String,
        var EMA: Double
)