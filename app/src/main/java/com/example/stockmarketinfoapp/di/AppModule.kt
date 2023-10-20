package com.example.stockmarketinfoapp.di

import android.app.Application
import androidx.room.Room
import com.example.stockmarketinfoapp.data.csv.CompanyListingParser
import com.example.stockmarketinfoapp.data.local.StockDatabase
import com.example.stockmarketinfoapp.data.remote.StockApi
import com.example.stockmarketinfoapp.data.repository.StockRepositoryImpl
import com.example.stockmarketinfoapp.domain.repository.StockRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {//this is DI for the whole app and we create db and api here so that its instantiated once and used thru the whole app

    @Provides
    @Singleton
    fun provideStockApi(): StockApi {
        return Retrofit.Builder()
            .baseUrl(StockApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideStockDatabase(app: Application): StockDatabase {
        return Room.databaseBuilder(
            app,
            StockDatabase::class.java,
            "stock.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideStockRepository(
      db: StockDatabase,
      api: StockApi
    ): StockRepository {
        return StockRepositoryImpl(
            api = api,
            dao = db.dao,
            companyListingParser = CompanyListingParser()
            )
    }


}