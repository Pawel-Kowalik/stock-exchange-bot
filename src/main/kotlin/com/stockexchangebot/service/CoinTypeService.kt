package com.stockexchangebot.service

import com.stockexchangebot.api.bittrex.publics.BittrexPublicFacade
import com.stockexchangebot.bittrexapi.dto.Currency
import com.stockexchangebot.model.postgres.CoinType
import com.stockexchangebot.model.postgres.CoinTypeDAO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import kotlin.streams.toList

@Service
class CoinTypeService @Autowired constructor(
        private val bittrexPublicFacade: BittrexPublicFacade,
        private val coinTypeDAO: CoinTypeDAO) {

    @Scheduled(cron = "0 0 0 * * *")
    fun saveCoinType() {
        val coinsType = getCoinTypeNames()
        coinTypeDAO.saveAll(coinsType)
    }

    fun getCoinTypeNames() : List<CoinType> {
        val currencies: Collection<Currency> = bittrexPublicFacade.getCurrencies() as List<Currency>
        return currencies.stream()
                .map { currency -> CoinType(currency.Currency) }
                .toList()
    }
}