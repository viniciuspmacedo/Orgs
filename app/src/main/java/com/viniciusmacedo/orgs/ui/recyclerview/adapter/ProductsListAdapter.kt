package com.viniciusmacedo.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.viniciusmacedo.orgs.R
import com.viniciusmacedo.orgs.model.Product

class ProductsListAdapter(
    products: List<Product>,
    val context: Context
) : RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {

    private val products = products.toMutableList()

    class ViewHolder (view: View): RecyclerView.ViewHolder(view) {
        fun binding(product: Product) {
            val name = itemView.findViewById<TextView>(R.id.product_item_name)
            name.text = product.name
            val description = itemView.findViewById<TextView>(R.id.product_item_description)
            description.text = product.description
            val price = itemView.findViewById<TextView>(R.id.product_item_price)
            price.text = product.price.toPlainString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.product_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.binding(product)
    }

    fun update(products: List<Product>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }

}