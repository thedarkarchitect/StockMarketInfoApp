package com.example.stockmarketinfoapp.presentation.companyListings

sealed class CompanyListingsEvent {
    data object Refresh: CompanyListingsEvent()
    data class OnSearchQueryChange(val query: String ): CompanyListingsEvent()//this is what the user will be typing into the search field
}
