package com.example.stockmarketinfoapp.data.remote

import retrofit2.http.Query

interface StockApi {
    suspend fun getListings(
        @Query("apikey") apiKey: String
    ): ResponseBody

    companion object {
        const val API_KEY = "BVF4IEBM4HZMS29F"
        const val BASE_URL = "https://alphavantage.co"
    }
}