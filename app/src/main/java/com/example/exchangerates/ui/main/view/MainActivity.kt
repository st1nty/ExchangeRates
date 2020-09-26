package com.example.exchangerates.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exchangerates.R
import com.example.exchangerates.data.api.RetrofitBuilder
import com.example.exchangerates.ui.base.ViewModelFactory
import com.example.exchangerates.ui.main.adapter.MainAdapter
import com.example.exchangerates.ui.main.viewmodel.MainViewModel
import androidx.lifecycle.Observer
import com.example.exchangerates.data.api.ApiHelper
import com.example.exchangerates.data.model.Currency
import com.example.exchangerates.data.model.ServerResponse
import com.example.exchangerates.utils.Status
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.currency_item.*
import kotlinx.android.synthetic.main.currency_item.view.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupViewModel()
        setupUI()
        setupObservers()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
                this,
                ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
                DividerItemDecoration(
                        recyclerView.context,
                        (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getAllCurrency().observe(this, Observer {
            it?.let {resource ->
                when (resource.status) {

                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { currencies -> retrieveList(currencies) }
                    }

                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }

                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }

            }
        })
    }



    private fun retrieveList(currencies: List<Currency>) {

        adapter.apply {
            addCurrencies(currencies)
            notifyDataSetChanged()
        }
    }
}