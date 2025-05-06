package com.example.weatherapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WeatherAdapter(
    private val data: List<Weather>,
    private val listener: (Weather) -> Unit
) : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(item: Weather, listener: (Weather) -> Unit) {
            itemView.findViewById<TextView>(R.id.cityText).text = item.city.city
            itemView.setOnClickListener { listener(item) }
        }
    }

    override fun onCreateViewHolder(...) = ViewHolder(LayoutInflater.from(...).inflate(R.layout.item_city, parent, false))
    override fun getItemCount() = data.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(data[position], listener)
}
