package com.example.stockmarketinfoapp.util

sealed class Screen(val route: String) {
    data object CompanyListingsScreen: Screen(route = "company_listings_screen")
    data object CompanyInfo: Screen( route = "company_Info")
}
