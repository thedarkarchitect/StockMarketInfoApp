package com.example.stockmarketinfoapp.data.csv

import java.io.InputStream

interface CustomCSVParser<T> { //setting the interface to be of a specific type so we can use it with custom types
    suspend fun parse(stream : InputStream): List<T>
}