package com.stockexchangebot

import com.stockexchangebot.model.postgres.CoinType
import com.stockexchangebot.service.CoinTypeService
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner


@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CoinTypeServiceTest {
    @Autowired private lateinit var coinTypeService: CoinTypeService

    @Test
    fun getCoinNamesTest() {
        val coinTypeNames: List<CoinType> = coinTypeService.getCoinTypeNames()
        Assert.assertFalse("List is empty", coinTypeNames.isEmpty())
    }
}