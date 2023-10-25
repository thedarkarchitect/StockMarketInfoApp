package com.example.stockmarketinfoapp.data.remote


import com.example.stockmarketinfoapp.data.remote.dto.CompanyInfoDto
import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

interface StockApi {
    @GET("query?function=LISTING_STATUS")
    suspend fun getListings(
        @Query("apikey") apiKey: String = API_KEY
    ): ResponseBody

    @GET("query?function=TIME_SERIES_INTRADAY&interval=60min&datatype=csv")//this return the close value of the market every 60mins
    suspend fun getIntradayInfo(//this is returned as a csv and must be converted using s csvParser
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY
    ): ResponseBody

    @GET("query?function=OVERVIEW")//this return a json of the company details
    suspend fun getCompanyInfo(
        @Query("symbol") symbol: String,
        @Query("apikey") apiKey: String = API_KEY
    ): CompanyInfoDto

    companion object {
        const val API_KEY = "BVF4IEBM4HZMS29F"
        const val BASE_URL = "https://alphavantage.co/"
    }
}