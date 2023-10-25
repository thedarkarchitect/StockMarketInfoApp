package com.example.stockmarketinfoapp.data.csv

import com.example.stockmarketinfoapp.data.mappers.toIntradayInfo
import com.example.stockmarketinfoapp.data.remote.dto.IntradayInfoDto
import com.example.stockmarketinfoapp.domain.model.CompanyListing
import com.example.stockmarketinfoapp.domain.model.IntradayInfo
import com.opencsv.CSVReader
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.InputStream
import java.io.InputStreamReader
import java.time.LocalDateTime

class IntradayInfoParser (): CustomCSVParser<IntradayInfo> { // injected in the repository module of the Di
    override suspend fun parse(stream: InputStream): List<IntradayInfo> {
        val csvReader = CSVReader(InputStreamReader(stream))
        return withContext(Dispatchers.IO) {
            csvReader
                .readAll()
                .drop(1)
                .mapNotNull {line ->
//                    create the columns of the data
                    val timestamp = line.getOrNull(0)
                    val close = line.getOrNull(4)
                    val dto = timestamp?.let { close?.let { it1 -> IntradayInfoDto(it, it1.toDouble()) } } //this convert the timestamp and close to take up new form if they are not null
                    dto?.toIntradayInfo()
                }.filter{//filter by day of the month
                    it.date.dayOfMonth == LocalDateTime.now().minusDays(1).dayOfMonth//this returns intraday info for yesterday by subtracting the current day by 1
                }.sortedBy {//sort the day data by the hour
                    it.date.hour
                }
                .also {
                    csvReader.close()
                }
        }
    }

}