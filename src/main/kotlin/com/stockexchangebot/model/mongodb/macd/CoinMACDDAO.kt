package com.stockexchangebot.model.mongodb.macd

import org.springframework.data.mongodb.repository.MongoRepository
import java.time.LocalDateTime

interface CoinMACDDAO : MongoRepository<CoinMACD, LocalDateTime>