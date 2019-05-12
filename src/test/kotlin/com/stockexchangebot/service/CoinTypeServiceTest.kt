package com.stockexchangebot.service

import com.stockexchangebot.SpringBootStockExchangeBotBasedProperties
import com.stockexchangebot.model.postgres.CoinType
import org.junit.Assert
import org.junit.Test
import org.springframework.beans.factory.annotation.Autowired


class CoinTypeServiceTest : SpringBootStockExchangeBotBasedProperties() {
    @Autowired private lateinit var coinTypeService: CoinTypeService

    @Test
    fun getCoinNamesTest() {
        val coinTypeNames: List<CoinType> = coinTypeService.getCoinTypeNames()
        Assert.assertFalse("List is empty", coinTypeNames.isEmpty())
    }
}