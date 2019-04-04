package com.stockexchangebot.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class CoinType (
        @Column(unique = true) private var coinName: String,
        @Id @GeneratedValue var coinTypeId: Long? = null

)