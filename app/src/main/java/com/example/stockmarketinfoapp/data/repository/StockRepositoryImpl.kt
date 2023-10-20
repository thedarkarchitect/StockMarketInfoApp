package com.example.stockmarketinfoapp.data.repository

import com.example.stockmarketinfoapp.data.local.StockDatabase
import com.example.stockmarketinfoapp.data.csv.CustomCSVParser
import com.example.stockmarketinfoapp.data.local.dao.StockDao
import com.example.stockmarketinfoapp.data.mappers.toCompanyListing
import com.example.stockmarketinfoapp.data.mappers.toCompanyListingEntity
import com.example.stockmarketinfoapp.data.remote.StockApi
import com.example.stockmarketinfoapp.domain.model.CompanyListing
import com.example.stockmarketinfoapp.domain.repository.StockRepository
import com.example.stockmarketinfoapp.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StockRepositoryImpl @Inject constructor(
    private val api: StockApi,
    private val dao: StockDao,
    private val companyListingParser: CustomCSVParser<CompanyListing>
): StockRepository {

    override suspend fun getCompanyListings(
        fetchFromRemote: Boolean,
        query: String
    ): Flow<Resource<List<CompanyListing>>> {
        return flow {
            emit(Resource.Loading(isLoading = true))
            //variable to call cached listings and make them available
            //basic check in the db before api call
            val localListings = dao.searchCompanyListing(query) //searches db for listings
            emit(Resource.Success(
                data = localListings.map {
                    it.toCompanyListing()//this changes the data to be used in UI
                }
            ))
            val isDbEmpty = localListings.isEmpty() && query.isBlank() // checks if the cache is empty
            val shouldJustLoadFromCache = !isDbEmpty && !fetchFromRemote //checks if the data is in the cache and an api was made to fill the cache(is there data in the cache)
            if(shouldJustLoadFromCache) {
                emit(Resource.Loading(false))
                return@flow
            }
            val remoteListings = try {
                val response = api.getListings() // this is an api call to get all the data from the remote server
                companyListingParser.parse(response.byteStream())
            } catch (e: IOException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(Resource.Error("Couldn't load data"))
                null
            }

            remoteListings?.let {listings ->
                dao.clearCompanyListings()//clear cache on insert of new cache(updated data)
                dao.insertCompanyListings(//data cached in the db
                    listings.map { it.toCompanyListingEntity() } // because data is going to the db
                )
                emit(Resource.Success(
                    data = dao
                        .searchCompanyListing("")
                        .map{
                            it .toCompanyListing()
                        }
                ))
                emit(Resource.Loading(isLoading = false))
            }
        }
    }
}