package com.stockexchangebot.model.postgres

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
data class CoinType (
        @Column(unique = true) var coinName: String,
        @Id @GeneratedValue var coinTypeId: Long? = null

)