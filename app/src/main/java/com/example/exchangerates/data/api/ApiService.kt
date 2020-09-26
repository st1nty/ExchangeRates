package com.example.exchangerates.data.api

import com.example.exchangerates.data.model.ServerResponse
import retrofit2.http.GET

interface ApiService {

    @GET("daily_json.js")
    suspend fun getAllCurrency(): ServerResponse
}