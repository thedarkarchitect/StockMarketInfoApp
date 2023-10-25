package com.example.stockmarketinfoapp.presentation.company_Info.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.stockmarketinfoapp.presentation.company_Info.CompanyInfoState
import com.example.stockmarketinfoapp.ui.theme.DarkBlue

@Composable
fun CompanyInfoScreen(
    modifier : Modifier = Modifier,
    state: CompanyInfoState
) {
    if(state.error == null){
        Column(
            modifier = modifier
                .fillMaxSize()
                .background(DarkBlue)
                .padding(16.dp)
        ){
            state.company?.let{company ->
                Text(
                   text = company.name,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    overflow = TextOverflow.Ellipsis,
                    modifier = modifier.fillMaxWidth()
                )
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    text = company.symbol,
                    fontStyle = FontStyle.Italic,
                    fontSize = 14.sp,
                    modifier = modifier.fillMaxWidth()
                )
                Spacer(modifier = modifier.height(8.dp))
                Divider(
                    modifier = modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    text = "Industry: ${company.industry}",
                    fontSize = 14.sp,
                    modifier = modifier.fillMaxWidth(),
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    text = "Country: ${company.country}",
                    fontSize = 14.sp,
                    modifier = modifier.fillMaxWidth(),
                    overflow = TextOverflow.Ellipsis
                )
                Spacer(modifier = modifier.height(8.dp))
                Divider(
                    modifier = modifier
                        .fillMaxWidth()
                )
                Spacer(modifier = modifier.height(8.dp))
                Text(
                    text = company.description,
                    fontSize = 12.sp,
                    modifier = modifier.fillMaxWidth(),
                )
                if(state.stockInfos.isNotEmpty()) {
                    Spacer(modifier = modifier.height(16.dp))
                    Text(text = "Market Summary")
                    Spacer(modifier = modifier.height(16.dp))
                    StockChart(
                        infos = state.stockInfos,
                        modifier = modifier
                            .fillMaxWidth()
                            .height(250.dp)
                            .align(CenterHorizontally)
                    )
                }
            }
        }
    }
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Center
    ){
        if(state.isLoading){
            CircularProgressIndicator()
        }else if(state.error != null) {
            Text(text = state.error, color = MaterialTheme.colorScheme.error)
        }
    }
}