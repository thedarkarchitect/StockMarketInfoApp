package com.example.stockmarketinfoapp.data.mappers

import com.example.stockmarketinfoapp.data.local.CompanyListingEntity
import com.example.stockmarketinfoapp.data.remote.dto.CompanyInfoDto
import com.example.stockmarketinfoapp.domain.model.CompanyInfo
import com.example.stockmarketinfoapp.domain.model.CompanyListing

fun CompanyListingEntity.toCompanyListing(): CompanyListing {//this will change the entity to data usuable in the model of the comapny listing
    return CompanyListing(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyListing.toCompanyListingEntity(): CompanyListingEntity {//changes data from the model(UI) to entity data
    return CompanyListingEntity(
        name = name,
        symbol = symbol,
        exchange = exchange
    )
}

fun CompanyInfoDto.toCompanyInfo(): CompanyInfo {
    return CompanyInfo(
        symbol = symbol ?: "",
        description = description ?: "",
        name = name ?: "",
        country = country ?: "",
        industry = industry ?: ""
    )
}