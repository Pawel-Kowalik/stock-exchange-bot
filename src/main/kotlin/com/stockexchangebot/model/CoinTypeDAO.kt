package com.stockexchangebot.model

import org.springframework.data.repository.CrudRepository

interface CoinTypeDAO : CrudRepository<CoinType, Long>