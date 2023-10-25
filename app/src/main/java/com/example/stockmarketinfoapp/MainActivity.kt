package com.example.stockmarketinfoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.stockmarketinfoapp.presentation.companyListings.components.CompanyListingsScreen
import com.example.stockmarketinfoapp.presentation.companyListings.CompanyListingsViewModel
import com.example.stockmarketinfoapp.presentation.company_Info.CompanyInfoViewModel
import com.example.stockmarketinfoapp.presentation.company_Info.components.CompanyInfoScreen
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
                    composable(
                        route = Screen.CompanyInfo.route + "/{symbol}",
                        arguments = listOf(navArgument("symbol") { type = NavType.StringType })
                    ){

                        val companyInfoViewModel = hiltViewModel<CompanyInfoViewModel>()
                        CompanyInfoScreen(
                            state = companyInfoViewModel.state
                        )
                    }
                }
            }
        }
    }
}
