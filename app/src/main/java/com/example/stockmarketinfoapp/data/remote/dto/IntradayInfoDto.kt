package com.example.stockmarketinfoapp.data.remote.dto

data class IntradayInfoDto(//information of the stock market in a single day
    val timestamp: String,
    val close: Double
)
