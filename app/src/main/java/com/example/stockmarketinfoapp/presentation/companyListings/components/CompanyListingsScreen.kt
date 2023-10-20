package com.example.stockmarketinfoapp.presentation.companyListings.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.stockmarketinfoapp.presentation.companyListings.CompanyListingsEvent
import com.example.stockmarketinfoapp.presentation.companyListings.CompanyListingsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompanyListingsScreen(
    modifier : Modifier = Modifier,
    navController: NavController,
    state: CompanyListingsState,
    onEvent: (CompanyListingsEvent) -> Unit
) {
//    val swipeRefreshState =

    Column(
        modifier = modifier.fillMaxSize()
    ){
        OutlinedTextField(
            value = state.searchQuery,
            onValueChange = {
                onEvent(CompanyListingsEvent.OnSearchQueryChange(it))
            },
            modifier = modifier
                .padding(16.dp)
                .fillMaxWidth(),
            placeholder = {
                Text(text="Search...")
            },
            maxLines = 1,
            singleLine = true
        )
        Spacer(modifier = modifier.height(8.dp))

        //implement refresh state
        LazyColumn(
            modifier = modifier.fillMaxSize()
        ){
            items(state.companies.size) { i ->
                val company = state.companies[i]
                CompanyItem(
                    company = company,
                    modifier = modifier
                        .fillMaxWidth()
                        .clickable {
                            //TODO:navigate detail
                        }
                        .padding(16.dp)
                )
                if(i < state.companies.size) {
                    Divider(modifier = modifier.padding(
                       horizontal = 16.dp
                    ))
                }
            }
        }
    }
}