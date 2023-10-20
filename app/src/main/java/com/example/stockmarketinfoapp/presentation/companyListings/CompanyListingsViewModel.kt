package com.example.stockmarketinfoapp.presentation.companyListings


import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.stockmarketinfoapp.domain.repository.StockRepository
import com.example.stockmarketinfoapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompanyListingsViewModel @Inject constructor(
    private val repository: StockRepository
): ViewModel() {

    var state by mutableStateOf(CompanyListingsState())

    private var searchJob: Job? = null // this is a coroutine to handle the search functionality

    init {
        getCompanyListings()
    }

    fun onEvent(event: CompanyListingsEvent) {
        when(event) {
            is CompanyListingsEvent.OnSearchQueryChange -> {//Note: Searching consumes cached data
                state = state.copy(searchQuery = event.query)//this sets the value of the query entered in the search field
                searchJob?.cancel()//this cancels the job as the event of entering query happens, on every character entry job is canceled until no more entering happens then coroutine to search happens with a half a second delay before search is initialized
                searchJob = viewModelScope.launch {//this is a different coroutine that happens after the searchJob coroutine finishes/canceled
                    delay(500L) //this is anly launched when the typing in the search field stops for half a second
                    getCompanyListings()//after half a second we get to make a call to db to get company listing
                }
            }
            CompanyListingsEvent.Refresh -> {//Note: refresh doesnot depend on search and gets updated data
                getCompanyListings(fetchFromRemote = true)//this triggers call to the apiwhen the query in typed into the search field
            }
        }
    }

    private fun getCompanyListings(
      query: String = state.searchQuery.lowercase(),
      fetchFromRemote: Boolean = false
    ){
        viewModelScope.launch {
            repository
                .getCompanyListings(fetchFromRemote, query)
                .collect{result ->
                    when(result) {
                        is Resource.Error -> Unit
                        is Resource.Loading -> {
                            state = state.copy(isLoading =  result.isLoading)
                        }
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                state = state.copy(
                                    companies = listings
                                )
                            }
                        }
                    }
                }
        }
    }

}