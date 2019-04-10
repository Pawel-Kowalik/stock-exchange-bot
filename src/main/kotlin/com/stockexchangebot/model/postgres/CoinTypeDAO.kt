package com.stockexchangebot.model.postgres

import org.springframework.data.repository.CrudRepository

interface CoinTypeDAO : CrudRepository<CoinType, Long>