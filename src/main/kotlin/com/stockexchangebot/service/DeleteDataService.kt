package com.stockexchangebot.service

import com.stockexchangebot.model.mongodb.coincalculatedata.CoinCalculateData
import com.stockexchangebot.model.mongodb.coincalculatedata.CoinCalculateDataDAO
import com.stockexchangebot.model.mongodb.signalmacd.CoinSignalMACD
import com.stockexchangebot.model.mongodb.signalmacd.SignalMACDDAO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class DeleteDataService @Autowired constructor(
    var coinCalculateDataDAO: CoinCalculateDataDAO,
    var signalMACDDAO: SignalMACDDAO){

//    @Scheduled(cron = "0 0/2 * * * *")
    private fun deleteOldCoinCalculateData() {
        val oldCoinCalculateData = getCoinCalculateData()

        if(oldCoinCalculateData.isNotEmpty())
            coinCalculateDataDAO.deleteAll(oldCoinCalculateData)
    }

    @Scheduled(cron = "0 0/2 * * * *")
    private fun deleteOldCoinEMA() {
        val  oldCoinEMA = getCoinEMA()

        if(oldCoinEMA.isNotEmpty())
            signalMACDDAO.deleteAll(oldCoinEMA)
    }

    private fun getCoinEMA() : Collection<CoinSignalMACD> {
        return signalMACDDAO.findAll().stream()
                .sorted { o1, o2 -> o1.dateTime.compareTo(o2.dateTime) }
                .limit((signalMACDDAO.findAll().size - 52).toLong())
                .collect(Collectors.toList())
    }

    private fun getCoinCalculateData() : Collection<CoinCalculateData> {
        return coinCalculateDataDAO.findAll().stream()
                .sorted { o1, o2 -> o1.dateTime.compareTo(o2.dateTime) }
                .limit((coinCalculateDataDAO.findAll().size - 52).toLong())
                .collect(Collectors.toList())
    }

}