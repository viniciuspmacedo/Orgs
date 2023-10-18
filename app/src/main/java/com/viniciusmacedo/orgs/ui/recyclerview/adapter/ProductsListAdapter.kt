package com.viniciusmacedo.orgs.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.viniciusmacedo.orgs.databinding.ProductItemBinding
import com.viniciusmacedo.orgs.extensions.formatToBRL
import com.viniciusmacedo.orgs.extensions.loadImage
import com.viniciusmacedo.orgs.model.Product

class ProductsListAdapter(
    products: List<Product>,
    val context: Context,
    var onCardClicked: (product: Product) -> Unit = {}
) : RecyclerView.Adapter<ProductsListAdapter.ViewHolder>() {

    private val products = products.toMutableList()

    inner class ViewHolder(private val binding: ProductItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private lateinit var product: Product
        init {
            itemView.setOnClickListener {
                if (::product.isInitialized){
                    onCardClicked(product)
                }
            }
        }

        fun adapterBinding(product: Product) {
            this.product = product
            binding.apply {
                val name = productItemName
                name.text = product.name
                val description = productItemDescription
                description.text = product.description
                val price = productItemPrice
                price.text = product.price.formatToBRL()

                val visibility = if (product.image != null) {
                    View.VISIBLE
                } else {
                    View.GONE
                }
                productItemImage.visibility = visibility

                productItemImage.loadImage(product.image, context)
            }

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ProductItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.adapterBinding(product)
    }

    fun update(products: List<Product>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }

}