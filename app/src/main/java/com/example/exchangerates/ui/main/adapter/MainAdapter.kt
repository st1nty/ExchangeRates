package com.example.exchangerates.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangerates.R
import com.example.exchangerates.data.model.Currency
import kotlinx.android.synthetic.main.currency_item.view.*

class MainAdapter(private val currencies: ArrayList<Currency>) :
    RecyclerView.Adapter<MainAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(currency: Currency) {
            itemView.apply {
                name.text = currency.name
                rate.text = currency.value.toString()
                name.isSelected = true
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.currency_item, parent, false))

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) = holder.bind(currencies[position])

    override fun getItemCount(): Int = currencies.size

    fun addCurrencies(currencies: List<Currency>) {
        this.currencies.apply {
            clear()
            addAll(currencies)
        }
    }
}

