package com.example.exchangerates.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getAllCurrency() = apiService.getAllCurrency()
}