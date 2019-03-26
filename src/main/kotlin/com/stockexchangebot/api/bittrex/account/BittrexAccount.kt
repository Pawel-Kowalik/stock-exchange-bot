package com.stockexchangebot.api.bittrex.account

import com.stockexchangebot.Utils
import com.stockexchangebot.api.reader.JsonReader
import com.stockexchangebot.dto.*
import java.math.BigDecimal

class BittrexAccount {
    var jsonReader: JsonReader = JsonReader()

    private val utils: Utils = Utils()
    private val url = "/account"
    private lateinit var uri: String

    fun getBalances() : Collection<*>{
        uri = String.format("%s%s/getbalances?apikey=%s", utils.bittrexApi, url, utils.key)
        return jsonReader.mapToSuitableDTOS(uri, true, Balance::class.java)
    }

    fun getBalance() : Any? {
        //TODO Add Coin type
        uri = String.format("%s%s/getbalance?apikey=%s&currency=%s", utils.bittrexApi, url, utils.key)
        return jsonReader.mapToSuitableDTO(uri, true, Balance::class.java)
    }

    fun getDepositAddress() : Any? {
        //TODO Add Coin type
        uri = String.format("%s%s/getdepositaddress?apikey=%s&currency=%s", utils.bittrexApi, url, utils.key)
        return jsonReader.mapToSuitableDTO(uri, true, DepositAddress::class.java)
    }

    fun withdraw(quantity: BigDecimal, address: String) : Any? {
        //TODO add coin type (currency)
        uri = String.format("%s%s/withdraw?apikey=%s&currency=%s&quantity=%s&address=%s", utils.bittrexApi, url,
                utils.key, /*currency,*/ quantity, address)
        return jsonReader.mapToSuitableDTO(uri, true, Withdraw::class.java)
    }

    fun getOrder(uuid: String) : Collection<*> {
        uri = String.format("%s%s/getorder&uuid=%s", utils.bittrexApi, url, uuid)
        return jsonReader.mapToSuitableDTOS(uri, false, Order::class.java)
    }

    fun getOrderHistory() : Collection<*> {
        String.format("%s%s/getorderhistory?apikey=%s&market=%s-%s", utils.bittrexApi, url, utils.key)
        return jsonReader.mapToSuitableDTOS(uri, false, OrderHistory::class.java)
    }

    fun getWithdrawalHistory() : Collection<*> {
        //TODO add coin type
        uri = String.format("%s%s/getwithdrawalhistory?apikey=%s&currency=%s", utils.bittrexApi, url, utils.key)
        return jsonReader.mapToSuitableDTOS(uri, true, WithdrawalHistory::class.java)
    }

    fun getDepositHistory() : Collection<*> {
        //TODO add coin type
        uri = String.format("%s%s/getdeposithistory?apikey=%s&currency=%s", utils.bittrexApi, url, utils.key)
        return jsonReader.mapToSuitableDTOS(uri, true, DepositHistory::class.java)
    }
}