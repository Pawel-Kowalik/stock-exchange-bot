package com.stockexchangebot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class StockExchangeBotApplication

fun main(args: Array<String>) {
    System.setProperty("server.servlet.context-path", "/")
    runApplication<StockExchangeBotApplication>(*args)
}