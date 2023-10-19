package com.example.stockmarketinfoapp.data.repository

import com.example.stockmarketinfoapp.data.StockDatabase
import com.example.stockmarketinfoapp.data.mappers.toCompanyListing
import com.example.stockmarketinfoapp.data.remote.StockApi
import com.example.stockmarketinfoapp.domain.model.CompanyListing
import com.example.stockmarketinfoapp.domain.repository.StockRepository
import com.example.stockmarketinfoapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    val api: StockApi,
    val db: StockDatabase
): StockRepository {

    private val dao = db.dao

    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            //variable to call cached listings and make them available
            val localListings = dao.searchCompanyListing(query) //searches db for listings
            emit(Resource.Success(
                data = localListings.map {
                    it.toCompanyListing()//this changes the data to be used in UI
                }
            ))
            val isDbEmpty = localListings.isEmpty() && query.isBlank()

        }
    }
}