package com.example.stockmarketinfoapp.domain.repository

import com.example.stockmarketinfoapp.domain.model.CompanyListing
import com.example.stockmarketinfoapp.util.Resource
import kotlinx.coroutines.flow.Flow

interface StockRepository {
    suspend fun getCompanyListings(
        fetchFromRemote: Boolean,//this boolean notifies repository if a api call is made
        query: String //this string is entered to make call to the api
    ): Flow<Resource<List<CompanyListing>>>
}