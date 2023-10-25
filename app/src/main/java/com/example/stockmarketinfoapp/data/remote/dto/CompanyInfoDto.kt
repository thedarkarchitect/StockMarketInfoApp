package com.example.stockmarketinfoapp.data.remote.dto

import com.squareup.moshi.Json

data class CompanyInfoDto(//these are details about the company
    @field:Json(name = "Symbol") val symbol: String?,//this is a moshi serialization by using @field
    @field:Json(name = "Description") val description: String?,
    @field:Json(name = "Name") val name: String?,
    @field:Json(name = "Country") val country: String?,
    @field:Json(name = "Industry") val industry: String?,
)
