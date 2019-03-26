package com.stockexchangebot.api.reader

import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.stockexchangebot.Utils
import com.stockexchangebot.dto.*
import com.stockexchangebot.dto.Currency
import org.apache.commons.codec.binary.Hex
import org.springframework.stereotype.Service
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL
import java.security.SecureRandom
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import javax.net.ssl.HttpsURLConnection


@Service
class JsonReader {
    val utils: Utils = Utils()

    fun <T>mapToSuitableDTO(uri: String, sign: Boolean, classToMap: Class<T>): Any? {
        var gson = Gson()
        var jsonObject = getJsonObject(uri, sign).getAsJsonObject("result")

        when(classToMap) {
            Ticker::class.java -> return gson.fromJson(jsonObject, Ticker::class.java)
//            OrderBook::class.java -> return gson.fromJson(jsonObject, OrderBook::class.java)
            BuyLimit::class.java -> return gson.fromJson(jsonObject, BuyLimit::class.java)
            SellLimit::class.java -> return gson.fromJson(jsonObject, SellLimit::class.java)
            Cancel::class.java -> return gson.fromJson(jsonObject, Cancel::class.java)
            Balance::class.java -> return gson.fromJson(jsonObject, Balance::class.java)
            DepositAddress::class.java -> return gson.fromJson(jsonObject, DepositAddress::class.java)
            Withdraw::class.java -> return gson.fromJson(jsonObject, Withdraw::class.java)

            else -> return null
        }
    }

    fun <T>mapToSuitableDTOS(uri: String, sign: Boolean, classToMap: Class<T>): Collection<Any> {
        var gson = Gson()
        var jsonArray = getJsonArray(uri, sign)

        when(classToMap) {
            Market::class.java -> return gson.fromJson(jsonArray, Array<Market>::class.java).toList()
            Currency::class.java -> return gson.fromJson(jsonArray, Array<Currency>::class.java).toList()
            MarketSummary::class.java -> return gson.fromJson(jsonArray, Array<MarketSummary>::class.java).toList()
//            OrderBook::class.java -> return gson.fromJson(jsonArray, Array<OrderBook>::class.java).toList()
            MarketHistory::class.java -> return gson.fromJson(jsonArray, Array<MarketHistory>::class.java).toList()
            OpenOrder::class.java -> return gson.fromJson(jsonArray, Array<OpenOrder>::class.java).toList()
            Balance::class.java -> return gson.fromJson(jsonArray, Array<Balance>::class.java).toList()
            OrderHistory::class.java -> return gson.fromJson(jsonArray, Array<OrderHistory>::class.java).toList()
            WithdrawalHistory::class.java -> return gson.fromJson(jsonArray, Array<WithdrawalHistory>::class.java).toList()
            DepositHistory::class.java -> return gson.fromJson(jsonArray, Array<DepositHistory>::class.java).toList()

            else -> return emptyList()
        }
    }

    private fun getJsonArray(uri: String, sign: Boolean) : JsonArray {
        var jsonObject = getJsonObject(uri, sign)

        return jsonObject.getAsJsonArray("result")
    }

    private fun getJsonObject(uri: String, sign: Boolean) : JsonObject {
        val jsonParser = JsonParser()
        lateinit var json: String

        if(sign)
            json = getJsonFromSignUrl(uri)
        else
            json = getJson(uri)

        return jsonParser.parse(json).asJsonObject
    }

    private fun getJson(uri: String) : String {
        val url = URL(uri)
        var scanner = Scanner(url.openStream())
        var json = scanner.nextLine()
        return json
    }

    private fun getJsonFromSignUrl(uri: String): String {
        val urlString = uri + "&nonce=" + generateNonce()

        val url = URL(urlString)
        val httpsURLConnection = url.openConnection() as HttpsURLConnection
        httpsURLConnection.requestMethod = "GET"
        httpsURLConnection.setRequestProperty("apisign", calculateHash(utils.secret, urlString, utils.encryptionAlgorithm))

        val reader = BufferedReader(InputStreamReader(httpsURLConnection.inputStream))

        return reader.readLine()
    }

    private fun calculateHash(secret: String, url: String, encryption: String): String {
        val shaHmac = Mac.getInstance(encryption)
        val secretKey = SecretKeySpec(secret.toByteArray(), encryption)
        shaHmac.init(secretKey)
        val hash = shaHmac.doFinal(url.toByteArray())

        return Hex.encodeHexString(hash)
    }

    private fun generateNonce(): String {
        val random = SecureRandom.getInstance("SHA1PRNG")
        random.setSeed(System.currentTimeMillis())

        val nonceBytes = ByteArray(16)
        random.nextBytes(nonceBytes)
        val nonce = String(Base64.getEncoder().encode(nonceBytes), Charsets.UTF_8)

        return nonce
    }

}