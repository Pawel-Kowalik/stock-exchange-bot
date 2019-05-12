package com.stockexchangebot.model.mongodb.signalmacd

import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime
import javax.persistence.Id

@Document
data class CoinSignalMACD (
        @Id var dateTime: LocalDateTime,
        var coinType: String,
        var EMA: Double
)