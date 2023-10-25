package com.example.stockmarketinfoapp.presentation.company_Info

import com.example.stockmarketinfoapp.domain.model.CompanyInfo
import com.example.stockmarketinfoapp.domain.model.IntradayInfo

data class CompanyInfoState(
    val stockInfos: List<IntradayInfo> = emptyList(),
    val company: CompanyInfo? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
