package com.example.exchangerates.ui.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.exchangerates.data.model.Currency
import com.example.exchangerates.data.model.ServerResponse
import com.example.exchangerates.data.repository.MainRepository
import com.example.exchangerates.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    fun getAllCurrency() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = getData(mainRepository.getAllCurrencies())))
        } catch (e: Exception) {
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }
}

fun getData(data: ServerResponse): ArrayList<Currency> {

    val currencyList = ArrayList<Currency>()

    for (entry in data.valute.entries) {
        val value = entry.value
        currencyList.add(value)
    }
    return currencyList
}
