package com.viniciusmacedo.orgs.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.viniciusmacedo.orgs.dao.ProductDao
import com.viniciusmacedo.orgs.databinding.ActivityProductsListBinding
import com.viniciusmacedo.orgs.ui.recyclerview.adapter.ProductsListAdapter

class ProductsListActivity : AppCompatActivity() {
    private val dao = ProductDao()
    private val adapter = ProductsListAdapter(
        context = this,
        products = dao.findAll()
    )

    private val binding by lazy {
        ActivityProductsListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpRecyclerView()
        setUpFab()
    }

    override fun onResume() {
        super.onResume()
        adapter.update(dao.findAll())
    }

    private fun setUpFab() {
        val fab = binding.floatingActionButton
        fab.setOnClickListener {
            openProductFormActivity()
        }
    }

    private fun openProductFormActivity() {
        val intent = Intent(this, ProductFormActivity::class.java)
        startActivity(intent)
    }

    private fun setUpRecyclerView() {
        val recycleView = binding.recyclerview
        recycleView.adapter = adapter
        adapter.onCardClicked = {product ->
           val intent = Intent(this, ProductDetailsActivity::class.java).apply {
               putExtra(PRODUCT_KEY, product)
           }
            startActivity(intent)
        }
        recycleView.layoutManager = LinearLayoutManager(this)
    }

}