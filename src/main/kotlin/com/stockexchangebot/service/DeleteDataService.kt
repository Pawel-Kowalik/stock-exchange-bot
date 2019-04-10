package com.stockexchangebot.service

import com.stockexchangebot.model.mongodb.coincalculatedata.CoinCalculateData
import com.stockexchangebot.model.mongodb.coincalculatedata.CoinCalculateDataDAO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class DeleteDataService @Autowired constructor(
    var coinCalculateDataDAO: CoinCalculateDataDAO ){

    @Scheduled(cron = "0 0 0 * * *")
    private fun deleteOldCoinCalculateData() {
        val deltaDay = LocalDate.now().minusDays(30)
        val oldCoinCalculateData = getCoinCalculateData(deltaDay)

        coinCalculateDataDAO.deleteAll(oldCoinCalculateData)
    }

    private fun getCoinCalculateData(deltaDay: LocalDate) : Collection<CoinCalculateData> {
        return coinCalculateDataDAO.getByDateBetween(LocalDate.now(), deltaDay)
    }
}