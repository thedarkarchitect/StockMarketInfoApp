package com.example.stockmarketinfoapp.domain.model

data class CompanyListing(//this will be accessed by the UI
    val name: String,
    val symbol: String,
    val exchange: String
)
