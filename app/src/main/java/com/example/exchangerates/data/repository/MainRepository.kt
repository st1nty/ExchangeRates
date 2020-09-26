package com.example.exchangerates.data.repository

import com.example.exchangerates.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getAllCurrencies() = apiHelper.getAllCurrency()
}