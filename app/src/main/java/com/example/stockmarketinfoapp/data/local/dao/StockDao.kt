package com.example.stockmarketinfoapp.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.stockmarketinfoapp.data.local.CompanyListingEntity
import com.example.stockmarketinfoapp.domain.model.CompanyListing

@Dao
interface StockDao {
    //instrustions to the db
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompanyListings(
        companyListings: List<CompanyListingEntity>
    )

    @Query("DELETE FROM companylistingentity")
    suspend fun clearCompanyListings()

    @Query(
        """
            SELECT * 
            FROM companylistingentity
            WHERE LOWER(name) LIKE '%' || LOWER(:query) || '%' OR 
                UPPER(:query) == symbol
        """
    )
    suspend fun searchCompanyListing(query:String): List<CompanyListingEntity>

}