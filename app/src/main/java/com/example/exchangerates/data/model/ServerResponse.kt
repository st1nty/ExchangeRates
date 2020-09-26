package com.example.exchangerates.data.model

import com.google.gson.annotations.SerializedName

data class ServerResponse (

    @SerializedName("Date") val date: String,
    @SerializedName("PreviousDate") val previousDate: String,
    @SerializedName("PreviousURL") val previousUrl: String,
    @SerializedName("Timestamp") val timestamp: String,
    @SerializedName("Valute") var valute: HashMap<String, Currency>,

)