package com.example.stockmarketinfoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.stockmarketinfoapp.presentation.companyListings.components.CompanyListingsScreen
import com.example.stockmarketinfoapp.presentation.companyListings.CompanyListingsViewModel
import com.example.stockmarketinfoapp.ui.theme.StockMarketInfoAppTheme
import com.example.stockmarketinfoapp.util.Screen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StockMarketInfoAppTheme {
                val navController = rememberNavController()
                val listingsViewModel = hiltViewModel<CompanyListingsViewModel>()
                NavHost(
                    navController = navController,
                    startDestination = Screen.CompanyListingsScreen.route
                ){
                    composable(
                        route = Screen.CompanyListingsScreen.route
                    ){
                        CompanyListingsScreen(
                            navController = navController,
                            state = listingsViewModel.state,
                            onEvent = listingsViewModel::onEvent
                        )
                    }
                }
            }
        }
    }
}
