package com.stockexchangebot.model.mongodb.coinsellema

import org.springframework.data.mongodb.repository.MongoRepository
import java.time.LocalDateTime

interface CoinEMADAO : MongoRepository<CoinEMA, LocalDateTime>