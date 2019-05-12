package com.stockexchangebot.model.mongodb.macd

import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import javax.persistence.Id

@Document
data class CoinMACD (
        @Id var dateTime: LocalDateTime,
        var coinType: String,
        var macdValue: Double
)