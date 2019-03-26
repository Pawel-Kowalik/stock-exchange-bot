package com.stockexchangebot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class StockExchangeBotApplication

fun main(args: Array<String>) {
    System.setProperty("server.servlet.context-path", "/")
    runApplication<StockExchangeBotApplication>(*args)
}