package com.stockexchangebot.service

import com.stockexchangebot.model.postgres.CoinTypeDAO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class SaveService @Autowired constructor(
        private var coinTypeDAO: CoinTypeDAO,
        private var calculateCoinPriceEMAService: CalculateCoinPriceEMAService,
        private var calculateMACDService: CalculateMACDService){

    @Scheduled(cron = "0/30 * * * * *")
    private fun saveData() {
        val coinTypes = coinTypeDAO.findAll().toList()

        calculateCoinPriceEMAService.saveCoinCalculateDataToMongo(coinTypes)
        calculateMACDService.calculateCoinsEMA(coinTypes)
    }
}