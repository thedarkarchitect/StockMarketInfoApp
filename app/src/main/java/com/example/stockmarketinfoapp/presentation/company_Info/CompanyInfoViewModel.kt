package com.example.stockmarketinfoapp.presentation.company_Info

import androidx.compose.runtime.*
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockmarketinfoapp.domain.repository.StockRepository
import com.example.stockmarketinfoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyInfoViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: StockRepository
): ViewModel() {
     var state by mutableStateOf(CompanyInfoState())

    init
    {
        val symbol = savedStateHandle.get<String>("symbol") ?: ""
        getCompanyInfo(symbol)

    }

    private fun getCompanyInfo(symbol: String){
        viewModelScope.launch {
//            val symbol = savedStateHandle.get<String>("symbol") ?: return@launch //from the saved state we need to get the symbol of the company clicked so this is the company state clicked onto be used to get company details and intraday data
            state = state.copy(isLoading = true)
//            since we in a coroutine we need to make the other requests their own coroutines so the each job happens on its own without blocking the top coroutine
            val companyInfoResult = async { repository.getCompanyInfo(symbol) } //these calls are made to the api without slowing down the viewModel coroutine
            val intradayInfoResult = async { repository.getIntradayInfo(symbol) }

            when( val result = companyInfoResult.await()) //this returns the result from the coroutine request made within the viewmodel coroutine
            {
                is Resource.Error -> {
                    state = state.copy(
                        company =  null,
                        isLoading = false,
                        error = result.message
                    )
                }
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    state = state.copy(
                        company = result.data,
                        isLoading = false,
                        error = null
                    )
                }
            }
            when( val result = intradayInfoResult.await()) //this returns the result from the coroutine request made within the viewmodel coroutine
            {
                is Resource.Error -> {
                    state = state.copy(
                        company =  null,
                        stockInfos = emptyList(),
                        isLoading = false,
                        error = result.message
                    )
                }
                is Resource.Loading -> Unit
                is Resource.Success -> {
                    state = state.copy(
                        stockInfos = result.data ?: emptyList(),
                        isLoading = false,
                        error = null
                    )
                }
            }
        }
    }
}