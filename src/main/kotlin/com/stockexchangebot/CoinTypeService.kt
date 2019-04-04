package com.stockexchangebot

import com.stockexchangebot.api.bittrex.publics.BittrexPublicFacade
import com.stockexchangebot.dto.bittrexapi.Currency
import com.stockexchangebot.model.CoinType
import com.stockexchangebot.model.CoinTypeDAO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import kotlin.streams.toList

@Service
class CoinTypeService @Autowired constructor(
        private val bittrexPublicFacade: BittrexPublicFacade,
        private val coinTypeDAO: CoinTypeDAO) {

    fun saveCoinType() {
        val coinsType = getCoinTypeNames()
        coinTypeDAO.saveAll(coinsType)
    }

    fun getCoinTypeNames() : List<CoinType> {
        var currencies: Collection<Currency> = bittrexPublicFacade.getCurrencies() as List<Currency>
        return currencies.stream()
                .map { currency -> CoinType(currency.Currency) }
                .toList()
    }
}