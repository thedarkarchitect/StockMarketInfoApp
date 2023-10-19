package com.example.stockmarketinfoapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.stockmarketinfoapp.data.local.CompanyListingEntity
import com.example.stockmarketinfoapp.data.local.dao.StockDao

@Database(
    entities = [CompanyListingEntity::class],
    version = 1,
    exportSchema = false
)
abstract class StockDatabase: RoomDatabase() {
    abstract val dao: StockDao
}