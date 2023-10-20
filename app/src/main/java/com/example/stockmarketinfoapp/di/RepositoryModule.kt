package com.example.stockmarketinfoapp.di

import com.example.stockmarketinfoapp.data.csv.CompanyListingParser
import com.example.stockmarketinfoapp.data.csv.CustomCSVParser
import com.example.stockmarketinfoapp.data.repository.StockRepositoryImpl
import com.example.stockmarketinfoapp.domain.repository.StockRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {//this is meant to inject classes into the repository created in the data layer




//    @Binds
//    @Singleton
//    abstract fun bindStockRepository(//this injects the repo implementation into company listings viewModel
//        stockRepositoryImpl: StockRepositoryImpl
//    ): StockRepository

//    @Binds
//    @Singleton
//    abstract fun bindCompanyListingsParser(
//        companyListingParser: CompanyListingParser
//    ): CustomCSVParser<CompanyListingParser>
}