package com.viniciusmacedo.orgs.dao

import com.viniciusmacedo.orgs.model.Product

class ProductDao {

    fun add(product: Product) {
        products.add(product)
    }

    fun findAll(): List<Product> {
        return products.toList()
    }

    companion object {
        private val products = mutableListOf<Product>()
    }
}