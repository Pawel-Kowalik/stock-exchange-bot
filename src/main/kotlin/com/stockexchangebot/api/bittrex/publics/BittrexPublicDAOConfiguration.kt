package com.stockexchangebot.api.bittrex.publics

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BittrexPublicDAOConfiguration {

    @Bean
    fun bittrexPublic() : BittrexPublicDAO {
        return BittrexPublic()
    }
}