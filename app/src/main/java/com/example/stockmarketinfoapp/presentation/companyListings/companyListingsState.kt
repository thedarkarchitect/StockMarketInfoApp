package com.example.stockmarketinfoapp.presentation.companyListings

import com.example.stockmarketinfoapp.domain.model.CompanyListing

data class CompanyListingsState(
    val companies: List<CompanyListing> = emptyList(), //initial state will be empty list before the calls are made
    val isLoading : Boolean = false,
    val isRefreshing: Boolean = false,
    val searchQuery: String = ""
)
