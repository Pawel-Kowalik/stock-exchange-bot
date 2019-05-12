package com.stockexchangebot.model.mongodb.signalmacd

import org.springframework.data.mongodb.repository.MongoRepository
import java.time.LocalDateTime

interface SignalMACDDAO : MongoRepository<CoinSignalMACD, LocalDateTime>